package io.arukas.security.endpoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 23:13
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;
}
