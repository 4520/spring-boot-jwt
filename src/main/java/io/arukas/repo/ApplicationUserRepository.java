package io.arukas.repo;

import io.arukas.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * ApplicationUser: 牛玉贤
 * Date: 2018/7/17
 * Time: 17:14
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUsername(String username);

}
