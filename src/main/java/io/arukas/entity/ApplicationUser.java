package io.arukas.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * ApplicationUser: 牛玉贤
 * Date: 2018/7/17
 * Time: 17:12
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Data
@Entity
@Table(name = "sys_user")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;


}
