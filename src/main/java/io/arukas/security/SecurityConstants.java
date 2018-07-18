package io.arukas.security;

/**
 * Created by IntelliJ IDEA.
 * ApplicationUser: 牛玉贤
 * Date: 2018/7/17
 * Time: 17:22
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
public class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";

    public static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    // 用户注册
    public static final String SIGN_UP_URL = "/users/sign-up";

}
