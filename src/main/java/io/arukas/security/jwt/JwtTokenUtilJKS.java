package io.arukas.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/18
 * Time: 15:32
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
public class JwtTokenUtilJKS {

    private static InputStream inputStream = Thread.currentThread()
            .getContextClassLoader().getResourceAsStream("jwt.jks"); // 寻找证书文件
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    private static final String alias = "serverkey";

    private static final String password = "nodenode";


    static { // 将证书文件里边的私钥公钥拿出来
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, password.toCharArray());

            // (别名, 密码)
            privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray()); // serverkey 为 命令生成文件时的别名

            // （别名）
            publicKey = keyStore.getCertificate(alias).getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回密钥对
     *
     * @return
     */
    public static KeyPair getKeyPair() {
        return new KeyPair(publicKey, privateKey);
    }


    /**
     * 返回公钥
     *
     * @return
     */
    public static PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * 返回私钥
     *
     * @return
     */
    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    public static String generateToken(String subject, int expirationSeconds, String salt) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
//                .signWith(SignatureAlgorithm.HS512, salt) // 不使用公钥私钥
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public static String parseToken(String token, String salt) {
        String subject = null;
        try {
            Claims claims = Jwts.parser()
//                    .setSigningKey(salt) // 不使用公钥私钥
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

    /**
     * 导出私钥
     *
     * @param path
     * @throws Exception
     */
    public static void exportPrivate(String path) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(privateKey.getEncoded());
        FileWriter fw = new FileWriter(new File(path));
        // \r\n --> windows
        // \n   --> linux
        // \r   --> mac
        fw.write("-----BEGIN PRIVATE KEY-----\r\n");
        fw.write(encoded);
        fw.write("\r\n");
        fw.write("-----END PRIVATE KEY-----");
        fw.close();
    }

    public static void exportPublic(String path) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(publicKey.getEncoded());
        FileWriter fw = new FileWriter(path);
        fw.write("-----BEGIN CERTIFICATE-----\r\n");
        fw.write(encoded);
        fw.write("\r\n");
        fw.write("-----END CERTIFICATE-----");
        fw.close();
    }

    public static void main(String[] args) throws Exception {
//        PublicKey publicKey = JwtTokenUtilJKS.getPublicKey();
//        System.out.println(publicKey);

//        PrivateKey privateKey = JwtTokenUtilJKS.getPrivateKey();
//        System.out.println(privateKey.getAlgorithm());
//        System.out.println(privateKey.getFormat());

        // 导出私钥
        JwtTokenUtilJKS.exportPrivate("D:/exported-private.key");
        JwtTokenUtilJKS.exportPublic("D:/exported-public.key");
    }

}
