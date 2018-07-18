package io.arukas.controller;

import com.alibaba.fastjson.JSON;
import io.arukas.SpringBootJwtApplicationTests;
import io.arukas.entity.ApplicationUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * ApplicationUser: 牛玉贤
 * Date: 2018/7/17
 * Time: 17:19
 * Email: ncc0706@gmail.com
 * To change this template use File | Settings | File Templates.
 */
public class UserEndpointTest extends SpringBootJwtApplicationTests {

    public MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void signUp() throws Exception {

        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername("admin");
        applicationUser.setPassword("admin");

        // 接受json格式的数据
        mockMvc.perform(post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(JSON.toJSONString(applicationUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.username", is(applicationUser.getUsername())))
                .andDo(print());

    }
}