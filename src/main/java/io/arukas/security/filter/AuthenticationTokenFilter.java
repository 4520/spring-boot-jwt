package io.arukas.security.filter;

import io.arukas.config.Config;
import io.arukas.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 22:53
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private Config config;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authToken = request.getHeader(config.getJwt().getHeader());
        if (authToken != null && authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }

        String username = jwtTokenUtil.verify(authToken);

        logger.info("checking authentication for user " + username);

        // token校验成功则不再需要验证
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
