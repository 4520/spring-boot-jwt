package io.arukas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 23:34
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hexo")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

}
