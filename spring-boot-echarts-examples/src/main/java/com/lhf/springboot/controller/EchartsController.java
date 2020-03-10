package com.lhf.springboot.controller;


import com.lhf.springboot.domain.FruitEcharts;
import com.lhf.springboot.domain.Points;
import com.lhf.springboot.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EcharsController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/3 12:01
 */
@Controller
public class EchartsController {

    @RequestMapping(value = "/index")
    public String index(){

        return "echarts";
    }

    @RequestMapping("/echarts")
    public String myEcharts(Model model){
        String skirt = "吊带裙";
        int nums = 40;

        model.addAttribute("skirt", skirt);
        model.addAttribute("nums", nums);

        return "echarts1";
    }


    @RequestMapping("/productList")
    @ResponseBody
    public List<Product>  products(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("丝袜", 23));
        productList.add(new Product("短裙", 35));
        productList.add(new Product("长裙", 45));
        productList.add(new Product("吊带", 29));
        productList.add(new Product("文胸", 55));
        productList.add(new Product("抹胸", 49));

        return productList;

    }

    //http://localhost:8099/echartsList
    @RequestMapping("/echartsList")
    public String echartsList(){
        return "echartsList";
    }

    @RequestMapping("/pie")
    public String pie(){
        return "echarts_pie";
    }

    @RequestMapping("/funnel")
    public String funnel(){
        return "echarts_funnel";
    }

    @RequestMapping(value = "/points")
    public String points(){
        return "echarts_line";
    }

    @RequestMapping("/pointsData")
    @ResponseBody
    public List<Points> pointsData(){
        List<Points> pointsList = new ArrayList<>();
        pointsList.add(new Points(-15, 0));
        pointsList.add(new Points(-50, 10));
        pointsList.add(new Points(-56, 20));
        pointsList.add(new Points(-40, 30));
        pointsList.add(new Points(-20, 40));
        pointsList.add(new Points(10, 60));

        return pointsList;

    }

    @RequestMapping("/line")
    public String line(){
        return "echarts_line1";
    }


    @RequestMapping(value = "/fruit", method = RequestMethod.GET)
    public String echarts(Model model){
        return "fruitList";
    }

    @RequestMapping(value = "/fruitList")
    @ResponseBody
    public List<FruitEcharts> echartsShow(Model model){

        List<FruitEcharts> fruitEcharts = new ArrayList<>();
        fruitEcharts.add(new FruitEcharts("苹果", 20));
        fruitEcharts.add(new FruitEcharts("芒果", 30));
        fruitEcharts.add(new FruitEcharts("雪梨", 40));
        fruitEcharts.add(new FruitEcharts("西瓜", 10));
        fruitEcharts.add(new FruitEcharts("哈密瓜", 50));


        System.out.println("fruitEcharts= " + fruitEcharts);
        return fruitEcharts;
    }



}
