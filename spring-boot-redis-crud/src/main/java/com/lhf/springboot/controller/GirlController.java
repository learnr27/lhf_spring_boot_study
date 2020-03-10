package com.lhf.springboot.controller;

import com.lhf.springboot.pojo.Girl;
import com.lhf.springboot.service.GirlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: GirlController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/25 17:23
 */
@Api(value = "操作Redis API接口")
@RestController
@RequestMapping(value = "/api")
public class GirlController {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GirlService girlService;

    @ApiOperation("添加女孩信息")
    @PostMapping("/add")
    public boolean addGirl(@RequestBody Girl girl){
        logger.info("开始添加女孩信息....");
        return girlService.addGirl(girl);
    }

    @ApiOperation("更新女孩信息")
    @PutMapping("/update")
    public boolean updateGirl(@RequestBody Girl girl){
        logger.info("开始更新女孩信息....");
        return girlService.updateGirl(girl);
    }

    @ApiOperation("删除女孩信息")
    @DeleteMapping("/delete")
    public boolean deleteGirl(@RequestParam(value = "id", required = true) int id){
        logger.info("开始删除女孩信息....");
        return girlService.deleteGirl(id);
    }

    @ApiOperation("获取女孩信息")
    @GetMapping("/girl")
    public Girl findByGirl(@RequestParam(value = "id", required = true) int id){
        logger.info("获取女孩信息....");
        return girlService.findById(id);
    }

    @ApiOperation("获取所有女孩信息")
    @GetMapping("/all")
    public List<Girl> girlList(){
        logger.info("获取所有女孩的信息....");
        return girlService.findByGirlList();
    }
}
