package com.lhf.springboot.controller;

import com.lhf.springboot.entity.Movies;
import com.lhf.springboot.mapper.MoviesMapper;
import com.lhf.springboot.service.MoviesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: MoviesController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 15:01
 */
@Api("电影API接口")
@Controller
@RequestMapping("/movie")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;


    @ApiOperation("电影列表Api")
    @GetMapping("/list")
    public List<Movies> findByAll(){
        return moviesService.findByAll();
    }

    @ApiOperation("根据电影上映序列码查询电影")
    @GetMapping("/findByMovieCode/{movieCode}")
    public Movies findByMovieCode(@PathVariable("movieCode") String movieCode){
        return moviesService.findByMovieCode(movieCode);
    }

    @ApiOperation("根据电影名查询电影信息")
    @GetMapping("/findByMovieName")
    public Movies findByMovieName(@RequestParam(value = "movieName", required = true)String movieName){
        return moviesService.findByMovieName(movieName);
    }

    @ApiOperation("添加电影信息")
    @PostMapping("/addMovie")
    public void addMovie(@RequestBody Movies movies){
        moviesService.addMovies(movies);
    }

    @ApiOperation("更新电影信息")
    @PostMapping("/updateMovie")
    public void updateMovie(@RequestBody Movies movies){
        moviesService.updateMovies(movies);
    }

    @DeleteMapping("/delete/{movieCode}")
    public void deleteByMovieCode(@PathVariable("movieCode") String movieCode){
        moviesService.deleteByMovieCode(movieCode);
    }


}

