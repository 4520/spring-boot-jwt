package io.arukas.security.endpoint;

import io.arukas.security.AccessToken;
import io.arukas.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 23:11
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class AuthenticationEndpoint {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @PostMapping(value = "${jwt.authenticationPath}")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        // Perform the security
        String username = request.getUsername();
        String password = request.getPassword();

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        final String token = jwtTokenUtil.generate(userDetails.getUsername());


        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(token);

//        accessToken.setExpiresIn();
        // Return the token
        return new AuthenticationResponse(token);
    }

}
