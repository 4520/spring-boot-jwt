package io.arukas.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.instrument.classloading.jboss.JBossLoadTimeWeaver;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/18
 * Time: 12:10
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
public class JwtHelper {

    //设置发行人
    private static final String ISSUER = "user";

    public static String genToken(Map<String, String> claims) {

        // 这里的JwtTokenUtilJKS是自定义的另一个工具类，用于从jks证书文件中提取公钥和私钥，进行RSA加密解密
        PrivateKey privateKey = JwtTokenUtilJKS.getPrivateKey();
        PublicKey publicKey = JwtTokenUtilJKS.getPublicKey();

        // 然后就是设置加密算法了，可以选择许多不同的加密算法，这里使用的RSA256非对称加密，安全性更好。如果为了方便，也可以使用HS256对称加密
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);


        Calendar now = Calendar.getInstance();

        // 5分钟之前的银行处理中的数据
        now.add(Calendar.HOUR, 1);
        Date time = now.getTime();

        //开始构建JWT，这里可以设置很多信息，我只设置了发行人和过期时间
        JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER).withExpiresAt(time);

        //然后把传入的claims放进builder里面，这里使用了java8的方法引用，也可以说是lambda的简化写法吧，本来写的lambda表达式，然后idea提示这里还可以简化，然后就变成这样子了。。
        claims.forEach(builder::withClaim);

        //签名之后返回
        return builder.sign(algorithm);
    }

}
