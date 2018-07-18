package io.arukas.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.arukas.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 22:42
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Component
public class JwtTokenUtil {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Config config;

    public String generate(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(config.getJwt().getSecret());
            return JWT.create()
                    .withIssuer(config.getJwt().getIssuer())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + config.getJwt().getExpiration() * 1000))
                    .withSubject(username)
                    .sign(algorithm);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String verify(String token) {
        if (token == null) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(config.getJwt().getSecret());
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(config.getJwt().getIssuer()).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

}
