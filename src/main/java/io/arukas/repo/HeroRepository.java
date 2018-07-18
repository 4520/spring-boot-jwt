package io.arukas.repo;

import io.arukas.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 23:35
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    //@Query("select h from Hero h where lower(h.name) like CONCAT('%', lower(:name), '%')")
    List<Hero> findByUsername(String username);

}
