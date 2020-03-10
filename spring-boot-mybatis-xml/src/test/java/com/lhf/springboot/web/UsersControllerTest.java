package com.lhf.springboot.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @ClassName: UsersControllerTest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/4 17:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/getUsers")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }
}
