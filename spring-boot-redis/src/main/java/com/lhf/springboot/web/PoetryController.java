package com.lhf.springboot.web;

import com.lhf.springboot.model.Poetry;
import com.lhf.springboot.pojo.PoetryParam;
import com.lhf.springboot.service.RedisServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName: PoetryController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/30 14:46
 */
@Api("Redis操作Api接口")
@Controller
public class PoetryController {

    @Autowired
    RedisServiceImpl redisService;

    @ApiOperation(value = "添加诗词到redis")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Poetry addPoetry(@RequestParam(value = "redisKey", required = true) @ApiParam("redis键") String redisKey,
                            @RequestParam(value = "title", required = true) @ApiParam("诗词标题") String title,
                            @RequestParam(value = "paragraphs", required = true) @ApiParam("词内容") String[] paragraphs,
                            @RequestParam(value = "author", required = true) @ApiParam("词作者") String author,
                            @RequestParam(value = "rhythmic", required = true) @ApiParam("词牌名") String rhythmic,
                            @RequestParam(value = "notes", required = true) @ApiParam("词注释") String[] notes){
        Poetry poetry = new Poetry();
        poetry.setRedisKey(redisKey);
        poetry.setTitle(title);
        poetry.setParagraphs(paragraphs);
        poetry.setAuthor(author);
        poetry.setRhythmic(rhythmic);
        poetry.setNotes(notes);

        System.out.println("poetry: " + poetry);

        //添加
        redisService.put(redisKey, poetry, -1);

        return poetry;
    }

    @ApiOperation(value = "添加诗词到redis")
    @RequestMapping(value = "/addList", method = RequestMethod.POST)
    @ResponseBody
    public Poetry addPoetryAll(@RequestBody Poetry poetry){

        System.out.println("poetry: " + poetry);

        //添加
        redisService.put(poetry.getRedisKey(), poetry, -1);

        return poetry;
    }

    @ApiOperation(value = "获取所有键值对")
    @GetMapping(value = "/getAll")
    @ResponseBody
    public Object getAll(){
        return redisService.getAll();
    }


    @ApiOperation(value = "获取所有key")
    @GetMapping(value = "/getKeys")
    @ResponseBody
    public Object getKeys(){
        return redisService.getKeys();
    }

    @ApiOperation(value = "根据key进行查询")
    @GetMapping(value = "/findByKey")
    @ResponseBody
    public Object get(){
        Poetry poetry = new Poetry();
        poetry.setRedisKey("poetry-001");
        return redisService.get(poetry.getRedisKey());
    }

    @ApiOperation(value = "根据key删除")
    @GetMapping("/remove")
    @ResponseBody
    public void remove(){
        Poetry poetry = new Poetry();
        poetry.setRedisKey("poetry-001");
        redisService.remove(poetry.getRedisKey());
    }

    @ApiOperation(value = "判断键是否存在")
    @GetMapping("/isKeyExists")
    @ResponseBody
    public void isKeyExists(){
        Poetry poetry = new Poetry();
        String redisKey = "poetry-001";
        poetry.setRedisKey(redisKey);
        boolean flag = redisService.isKeyExists(poetry.getRedisKey());
        System.out.println( redisKey + "是否存在: " + flag);
    }

    @ApiOperation(value = "查询当前缓存的数量")
    @GetMapping("/count")
    @ResponseBody
    public Object count(){
        return redisService.count();
    }

    @ApiOperation(value = "清空")
    @GetMapping(value = "/empty")
    @ResponseBody
    public void empty(){
        redisService.empty();
    }



}
