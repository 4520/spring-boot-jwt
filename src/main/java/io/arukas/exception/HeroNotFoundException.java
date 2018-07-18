package io.arukas.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 23:48
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@ResponseStatus(code = NOT_FOUND)
public class HeroNotFoundException extends RuntimeException{

    public HeroNotFoundException(String name) {
        this("Could not find hero with name '%s'", name);
    }

    public HeroNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }

}
