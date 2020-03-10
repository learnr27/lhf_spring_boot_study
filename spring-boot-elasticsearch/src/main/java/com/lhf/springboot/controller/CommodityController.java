package com.lhf.springboot.controller;

import com.lhf.springboot.entity.Commodity;
import com.lhf.springboot.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: CommodityController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/22 14:47
 */
@Api("ES入门demo API接口")
@RestController
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @ApiOperation("获取记录条数")
    @GetMapping("/count")
    public long getCount(){
        return commodityService.count();
    }

    @ApiOperation("添加商品信息")
    @PostMapping("/add")
    public void addCommodity(@RequestBody Commodity commodity){
        commodityService.save(commodity);
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/delete/{skuid}")
    public void deleteCommodity(@PathVariable("skuId") String skuId) {
        Commodity commodity = new Commodity();
        commodity.setSkuId(skuId);
        commodityService.delete(commodity);
    }

    @ApiOperation("获取商品列表")
    @GetMapping("/list")
    public List<Commodity> findByAll(){
        return (List<Commodity>) commodityService.getAll();
    }

    @ApiOperation("根据商品名查询商品信息")
    @GetMapping("/getByName")
    public List<Commodity> getByName(@RequestParam(value = "name", required = true) String name){
        return commodityService.getByName(name);
    }

    @ApiOperation("分页查询")
    @GetMapping("/pageQuery")
    public Page<Commodity> pageQuery(@RequestParam(value = "pageNo", required = true) Integer pageNo,
                                     @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                     @RequestParam(value = "kw", required = true) String kw){
        Page<Commodity> page = commodityService.pageQuery(pageNo,pageSize,kw);
        return page;
    }

}
