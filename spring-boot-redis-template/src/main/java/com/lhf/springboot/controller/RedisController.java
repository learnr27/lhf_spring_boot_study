package com.lhf.springboot.controller;

import com.lhf.springboot.service.RedisService;
import com.lhf.springboot.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: RedisController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/17 16:30
 */
@Api("Redis操作API")
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Resource
    private RedisService redisService;

    @ApiOperation("添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserVo addUser(@RequestBody UserVo userVo){
        valueOperations.set("user-" + userVo.getId(),userVo);
        System.out.println("userVo = " + userVo);

        return (UserVo) valueOperations.get("user-" + userVo.getId());
    }

    @ApiOperation("添加多个用户")
    @RequestMapping(value = "/addUsers", method = RequestMethod.POST)
    public List<UserVo> addUsers(@RequestBody List<UserVo> userVoList){
        for(UserVo list: userVoList){
            setOperations.add("user-" + list.getId(), list);
            Set<Object> result = setOperations.members("user-" + list.getId());
            System.out.println(result);
        }

        return userVoList;
    }

    @ApiOperation("添加用户到hash")
    @RequestMapping(value = "/hash", method = RequestMethod.POST)
    public void HashOperations(@RequestBody UserVo userVo) throws Exception{

        hashOperations.put("hash:user",userVo.hashCode()+"", userVo);
        System.out.println(hashOperations.get("hash:user",userVo.hashCode()+""));
    }


    @ApiOperation("添加用户到list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void  ListOperations(@RequestBody UserVo userVo) throws Exception{

        listOperations.leftPush("list:user",userVo);
        // pop之后 值会消失
        System.out.println(listOperations.leftPop("list:user"));
    }

    @ApiOperation("根据key获取user信息")
    @RequestMapping(value = "/key", method = RequestMethod.GET)
    public UserVo getValue(@RequestParam(value = "key", required = true) String key){
        return (UserVo) valueOperations.get(key);
    }

    @ApiOperation("修改key名，并返回数据")
    @RequestMapping(value = "/renameKey", method = RequestMethod.GET)
    public UserVo renameKey(@RequestParam(value = "oldKey", required = true) String oldKey,
                          @RequestParam(value = "newKey", required = true) String newKey){
        redisService.renameKey(oldKey, newKey);
        return (UserVo) valueOperations.get(newKey);
    }

    @ApiOperation("获取所有以 u 开头的key")
    @RequestMapping(value = "/getKeys", method = RequestMethod.GET)
    public Set<String> getKeys(){
        return stringRedisTemplate.keys("u*");
    }

}
