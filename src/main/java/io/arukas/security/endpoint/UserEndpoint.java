package io.arukas.security.endpoint;

import io.arukas.entity.ApplicationUser;
import io.arukas.repo.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * ApplicationUser: 牛玉贤
 * Date: 2018/7/17
 * Time: 17:16
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class UserEndpoint {

    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/users/sign-up")
    public ApplicationUser signUp(@RequestBody ApplicationUser user) {
        // 密码去空格
        user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
        return userRepository.save(user);
    }

}
