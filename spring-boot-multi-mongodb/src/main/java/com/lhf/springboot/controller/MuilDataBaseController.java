package com.lhf.springboot.controller;

import com.lhf.springboot.common.ResponseResult;
import com.lhf.springboot.model.Girl;
import com.lhf.springboot.repository.primary.PrimaryRepository;
import com.lhf.springboot.repository.secondary.SecondaryRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName: MuilDataBaseController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 10:11
 */
@Api(value = "MongoDB多数据源操作API", tags = "MongoDB操作实例")
@RestController
public class MuilDataBaseController {

    @Autowired
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;

    @ApiOperation(value = "添加单条女孩信息")
    @RequestMapping(value = "/addGirl", method = RequestMethod.POST)
    public ResponseResult addGirl(@RequestBody Girl girl){
       Girl girlData1 =  primaryRepository.save(girl);
       Girl girlData2 = secondaryRepository.save(girl);
       if(girlData1 != null && girlData2 != null){
           return ResponseResult.success("添加成功");
       }else {
           return ResponseResult.error("添加失败");
       }
    }

    @ApiOperation(value = "添加多条女孩信息")
    @RequestMapping(value = "/addGirlList", method = RequestMethod.POST)
    public ResponseResult addGirlList(@RequestBody List<Girl> girlList){
       List<Girl> girlList1 = primaryRepository.saveAll(girlList);
       List<Girl> girlList2 = secondaryRepository.saveAll(girlList);
       if(girlList1 != null && girlList2 != null){
            return ResponseResult.success("添加成功");
       }else {
            return ResponseResult.error("添加失败");
       }
    }

    @ApiOperation("根据女孩编号查询女孩信息")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Optional<Girl> findGirlById(@RequestParam(value = "id", required = true) Integer id){
        Optional<Girl> girl = primaryRepository.findById(id);
        if(girl == null || girl.toString() == "Optional.empty"){
            girl = secondaryRepository.findById(id);
        }
        return girl;
    }

    @ApiOperation("查询所有女孩信息")
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public List<Girl> findAllGils(){
       List<Girl> girlList = primaryRepository.findAll();
       return girlList;
    }

    @ApiOperation("根据id删除库1中的女孩")
    @DeleteMapping(value = "/delete1")
    public void deletePrimaryGirl(@RequestParam(value = "id", required = true) Integer id){
        primaryRepository.deleteById(id);
    }


    @ApiOperation("根据id删除库2中的女孩")
    @DeleteMapping(value = "/delete2")
    public void deleteSecondaryGirl(@RequestParam(value = "id", required = true) Integer id){
        secondaryRepository.deleteById(id);
    }



}
