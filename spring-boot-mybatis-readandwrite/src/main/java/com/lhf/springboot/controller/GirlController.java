package com.lhf.springboot.controller;

import com.lhf.springboot.entity.Girl;
import com.lhf.springboot.mapper.GirlMapper;
import com.lhf.springboot.response.WebResult;
import com.lhf.springboot.service.GirlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: GirlController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/28 16:55
 */
@Api(value = "女孩信息Api接口", tags = "女孩")
@RestController
public class GirlController {

    @Autowired
    private GirlService girlService;

    /*@Autowired
    private GirlMapper girlMapper;*/

    @ApiOperation("根据id查询女孩信息")
    @RequestMapping(value = "findByGirlId", method = RequestMethod.GET)
    public WebResult findByGirlId(@RequestParam Integer id){
        return WebResult.success(girlService.findByGrilId(id));
    }

    @ApiOperation("添加女孩信息")
    @RequestMapping(value = "/addGirl", method = RequestMethod.POST)
    public WebResult addGirl(@RequestBody Girl girl){
        if(girlService.addGirl(girl)){
            return WebResult.success();
        }else {
            return WebResult.error("添加失败");
        }
    }

    @ApiOperation("更新女孩信息")
    @RequestMapping(value = "/updateGirl", method = RequestMethod.POST)
    public WebResult updateGirl(@RequestBody Girl girl){
        if(girlService.updateGirl(girl)){
            return WebResult.success();
        }else {
            return WebResult.error("更新失败");
        }
    }

    @ApiOperation("查询所有女孩信息")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public WebResult selectAll(){
        return WebResult.success(girlService.selectAll());
    }

    @ApiOperation("删除女孩信息")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public WebResult deleteById(@RequestParam Integer id){
        if(girlService.deleteById(id)){
            return WebResult.success();
        }else {
            return WebResult.error("删除失败！");
        }
    }
}
