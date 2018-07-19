package io.arukas.security.jwt;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPublicKeySpec;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/18
 * Time: 12:15
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
public class JwtRsaUtil {

    private String keyStoreFile;
    private char[] password;
    private KeyStore store;
    private Object lock = new Object();

    private static JwtRsaUtil instance = null;

    public static JwtRsaUtil getInstance() {
        synchronized (JwtRsaUtil.class) {
            if (instance == null) {
                synchronized (JwtRsaUtil.class) {
                    //这里是jks文件路径和密码
                    instance = new JwtRsaUtil("/******.jks", "******".toCharArray());
                }
            }
            return instance;
        }
    }

    private JwtRsaUtil(String keyStoreFile, char[] password) {
        this.keyStoreFile = keyStoreFile;
        this.password = password;
    }

    public KeyPair getKeyPair() {
        return getKeyPair("", this.password);
    }

    public KeyPair getKeyPair(String alias, char[] password) {
        try {
            synchronized (this.lock) {
                if (this.store == null) {
                    synchronized (this.lock) {
                        InputStream is = this.getClass().getResourceAsStream(keyStoreFile);
                        try {
                            this.store = KeyStore.getInstance("JKS");
                            this.store.load(is, this.password);
                        } finally {
                            if (is != null) {
                                try {
                                    is.close();
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }
            }
            RSAPrivateCrtKey key = (RSAPrivateCrtKey) this.store.getKey(alias, password);
            RSAPublicKeySpec spec = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
            return new KeyPair(publicKey, key);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot load keys from store: " + this.keyStoreFile, e);
        }
    }


}
