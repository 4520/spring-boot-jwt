package io.arukas.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 牛玉贤
 * Date: 2018/7/17
 * Time: 23:49
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
@RestControllerAdvice
@Order(Integer.MAX_VALUE)
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccessException(DataAccessException exception) {
        logger.error(exception.getMessage(), exception);
        Map<String, Object> body = new HashMap<>();
        body.put("message", exception.getMessage());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationException exception) {
        logger.error(exception.getMessage(), exception);
        Map<String, Object> body = new HashMap<>();
        body.put("message", exception.getMessage());
        return ResponseEntity.ok().body(body);
    }

}
