package com.lhf.springboot.controller;

import com.lhf.springboot.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @ClassName: RestController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 15:49
 */
@Api("RestTemplate 用法API接口")
@RestController
public class RestTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateController.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * RestTemplate发送普通的Http请求
     * @param message
     * @return
     */
    @ApiOperation("RestTemplate发送普通的Http请求")
    @GetMapping("/sendMsg")
    public String sendMsg(@RequestParam(value = "message", required = true) String message){
        String result = restTemplate.getForObject("http://localhost:8096/msg?message="+message, String.class);
        return result;
    }

    /**
     * RestTemplate发送Post请求的三种方式
     * @param user
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("RestTemplate发送post请求")
    @PostMapping("/sendUser")
    public ResponseEntity<User> postUser(@RequestBody User user) throws URISyntaxException {
        // 1-postForObject()
        User user1 = this.restTemplate.postForObject("http://localhost:8096/user", user, User.class);
        logger.info("发送post请求1：{}", user1);


        // 2-postForEntity()
        ResponseEntity<User> responseEntity1 = this.restTemplate.postForEntity("http://localhost:8096/user", user, User.class);
        logger.info("发送post请求2：{}", responseEntity1);

        // 3-exchange()
        RequestEntity<User> requestEntity = RequestEntity.post(new URI("http://localhost:8096/user")).body(user);
        ResponseEntity<User> responseEntity2 = this.restTemplate.exchange(requestEntity, User.class);
        logger.info("发送post请求3：{}", responseEntity2);

        return responseEntity1;
    }


    /**
     * RestTemplate发送get请求的三种方式
     * @param id
     * @param name
     * @param password
     * @param phone
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("RestTemplate发送get请求")
    @GetMapping("/sendUser")
    public User getUser(@RequestParam(value = "id", required = true) Integer id,
                        @RequestParam(value = "name", required = true)String name,
                        @RequestParam(value = "password", required = true)String password,
                        @RequestParam(value = "phone", required = true)String phone) throws URISyntaxException {
        String user = "id="+id+"&name="+name+"&password="+password+"&phone="+phone;

        // 1-getForObject()
        User user1 = this.restTemplate.getForObject("http://localhost:8096/user?" + user, User.class);
        logger.info("发送get请求1：{}", user1);

        // 2-getForEntity()
        ResponseEntity<User> responseEntity1 = this.restTemplate.getForEntity("http://localhost:8096/user?"+user, User.class);
        HttpStatus statusCode = responseEntity1.getStatusCode();
        HttpHeaders header = responseEntity1.getHeaders();
        User user2 = responseEntity1.getBody();
        logger.info("发送get请求2：{}, statusCode = {}, header = {}", user2, statusCode, header );

        // 3-exchange()
        RequestEntity requestEntity = RequestEntity.get(new URI("http://localhost:8096/user?"+user)).build();
        ResponseEntity<User> responseEntity2 = this.restTemplate.exchange(requestEntity, User.class);
        User user3 = responseEntity2.getBody();
        logger.info("发送get请求3：{}", user3);

        return user3;

    }

    /**
     * RestTemplate设置Http Header
     * @param user
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("RestTemplate设置Http Header")
    @PostMapping("/setHeader")
    public RequestEntity<User> headerUser(@RequestBody User user) throws URISyntaxException {
        // 1-Content-Type
        RequestEntity<User> requestEntity = RequestEntity
                .post(new URI("http://localhost:8096/user"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(user);
        logger.info("1-设置设置HTTP Header: {}", requestEntity);

        // 2-Accept
        RequestEntity<User> requestEntity1 = RequestEntity
                .post(new URI("http://localhost:8096/user"))
                .accept(MediaType.APPLICATION_JSON)
                .body(user);
        logger.info("2-设置设置HTTP Header: {}", requestEntity1);

        // 3-Other
        RequestEntity<User> requestEntity2 = RequestEntity
                .post(new URI("http://localhost:8096/user"))
                .header("Authorization", "Basic ")
                .body(user);
        logger.info("3-设置设置HTTP Header: {}", requestEntity2);

        return requestEntity;
    }

}
