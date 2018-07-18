package io.arukas.security.endpoint;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 23:13
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Data
public class AuthenticationRequest {

    private String username;

    private String password;

}
