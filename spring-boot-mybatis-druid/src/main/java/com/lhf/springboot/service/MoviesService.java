package com.lhf.springboot.service;

import com.lhf.springboot.entity.Movies;

import java.util.List;

/**
 * @ClassName: MoviesService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 14:53
 */
public interface MoviesService {

    void addMovies(Movies movies);

    void updateMovies(Movies movies);

    void deleteByMovieCode(String movieCode);

    List<Movies> findByAll();

    Movies findByMovieCode(String movieCode);

    Movies findByMovieName(String movieName);
}
