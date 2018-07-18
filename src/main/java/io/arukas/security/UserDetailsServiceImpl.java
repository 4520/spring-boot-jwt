package io.arukas.security;

import io.arukas.entity.ApplicationUser;
import io.arukas.entity.Authority;
import io.arukas.repo.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * ApplicationUser: 牛玉贤
 * Date: 2018/7/17
 * Time: 17:25
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
        // username, password, authorities
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), Collections.emptyList());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
