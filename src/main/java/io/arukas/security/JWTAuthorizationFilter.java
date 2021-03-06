package io.arukas.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.arukas.security.SecurityConstants.HEADER_STRING;
import static io.arukas.security.SecurityConstants.TOKEN_PREFIX;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 18:00
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {

            // parse the token.
            // android jtw client.
//            String user = Jwts.parser()
//                    .setSigningKey(SECRET.getBytes())
//                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//                    .getBody()
//                    .getSubject();
//            if (user != null) {
//                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//            }
            return null;
        }
        return null;

    }

}
