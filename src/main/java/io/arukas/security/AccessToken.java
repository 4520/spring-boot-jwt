package io.arukas.security;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/18
 * Time: 11:59
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Data
public class AccessToken {

    private String accessToken;

    private String tokenType;

    private long expiresIn;

}
