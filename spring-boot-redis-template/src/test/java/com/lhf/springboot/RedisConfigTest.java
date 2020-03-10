package com.lhf.springboot;

import com.lhf.springboot.service.RedisService;
import com.lhf.springboot.util.RedisKeyUtil;
import com.lhf.springboot.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisConfigTest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/17 16:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

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

    @Test
    public void testObj() throws Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("上海");
        userVo.setName("测试dfas");
        userVo.setAge(123);
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        redisService.expireKey("name",20, TimeUnit.SECONDS);
        String key = RedisKeyUtil.getKey(UserVo.Table,"name",userVo.getName());
        System.out.println("key = " + key);
        UserVo vo = (UserVo) operations.get(key);
        System.out.println(vo);
    }

    @Test
    public void testValueOption( )throws  Exception{
        UserVo userVo = new UserVo();
        userVo.setId(100);
        userVo.setAddress("上海");
        userVo.setName("jantent");
        userVo.setPhone("18295514444");
        userVo.setEmail("27633563@qq.com");
        userVo.setAge(23);
        valueOperations.set("test",userVo);

        System.out.println(valueOperations.get("test"));
    }

    @Test
    public void testSetOperation() throws Exception{
        UserVo userVo = new UserVo();
        userVo.setId(101);
        userVo.setAge(23);
        userVo.setName("jantent");
        userVo.setPhone("13765651721");
        userVo.setEmail("2652266@qq.com");
        userVo.setAddress("北京");

        UserVo auserVo = new UserVo();
        userVo.setId(102);
        auserVo.setName("antent");
        auserVo.setAge(23);
        userVo.setPhone("13765699721");
        userVo.setEmail("2652288@qq.com");
        auserVo.setAddress("湖北武汉");

        setOperations.add("user:test",userVo,auserVo);
        Set<Object> result = setOperations.members("user:test");
        System.out.println(result);
    }

    @Test
    public void HashOperations() throws Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("北京");
        userVo.setName("jantent");
        userVo.setAge(23);
        hashOperations.put("hash:user",userVo.hashCode()+"",userVo);
        System.out.println(hashOperations.get("hash:user",userVo.hashCode()+""));
    }

    @Test
    public void  ListOperations() throws Exception{
        UserVo userVo = new UserVo();
        userVo.setAddress("北京");
        userVo.setName("jantent");
        userVo.setAge(23);
//        listOperations.leftPush("list:user",userVo);
//        System.out.println(listOperations.leftPop("list:user"));
        // pop之后 值会消失
        System.out.println(listOperations.leftPop("list:user"));
    }
}
