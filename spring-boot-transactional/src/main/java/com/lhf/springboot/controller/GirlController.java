package com.lhf.springboot.controller;

import com.lhf.springboot.dao.GirlDao;
import com.lhf.springboot.pojo.Girl;
import com.lhf.springboot.service.GirlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: GirlController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 11:48
 */
@Api(value = "女孩Api接口", tags = "女孩")
@RestController
@RequestMapping(value = "/api")
public class GirlController {

    @Autowired
    private GirlService girlService;

    @Autowired
    private GirlDao girlDao;

    @ApiOperation("添加女孩信息")
    @PostMapping("/addGirl")
    public String addGirl(@RequestBody Girl girl){
        int num = girlDao.insert(girl);
        if(num > 0){
            return "添加数据成功！";
        }else {
            return "添加数据失败！";
        }
    }

    @ApiOperation("事务测试1")
    @PostMapping("/girl1")
    public boolean girl1(@RequestBody Girl girl){
        System.out.println("请求参数：" + girl);
        try{
            girlService.girl1(girl);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("最后查询的数据：" + girlDao.findByGirlId(girl.getId()));
        return true;
    }

    @ApiOperation("事务测试2")
    @PostMapping("/girl2")
    public boolean girl2(@RequestBody Girl girl){
        System.out.println("请求参数：" + girl);
        girlService.girl2(girl);
        System.out.println("最后查询的数据：" + girlDao.findByGirlId(girl.getId()));
        return true;
    }

    @ApiOperation("事务测试3")
    @PostMapping("/girl3")
    public boolean girl3(@RequestBody Girl girl){
        System.out.println("请求参数：" + girl);
        girlService.girl3(girl);
        System.out.println("最后查询的数据：" + girlDao.findByGirlId(girl.getId()));
        return true;
    }

    @ApiOperation("事务测试4")
    @PostMapping("/girl4")
    public boolean girl4(@RequestBody Girl girl){
        System.out.println("请求参数：" + girl);
        girlService.girl4(girl);
        System.out.println("最后查询的数据：" + girlDao.findByGirlId(girl.getId()));
        return true;
    }

    @ApiOperation("查询所有女孩信息")
    @GetMapping("/list")
    public List<Girl> findByAll(){
       return girlDao.fingByAll();
    }

    @ApiOperation("根据id删除女孩")
    @DeleteMapping("/delete")
    public void deleteGirl(@RequestParam(value = "id" , required = true) Integer id){
        girlDao.delete(id);
    }

}
