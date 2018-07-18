package io.arukas.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 22:06
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Data
@Component
@ConfigurationProperties
public class Config {

    private Cors cors = new Cors();

    private Jwt jwt = new Jwt();

    @Data
    public static class Cors {

        private List<String> allowedOrigins = new ArrayList<>();

        private List<String> allowedMethods = new ArrayList<>();

        private List<String> allowedHeaders = new ArrayList<>();
    }

    @Data
    public static class Jwt {

        private String header;

        private String secret;

        private Long expiration;

        private String issuer;

        private String authenticationPath;

    }

}
