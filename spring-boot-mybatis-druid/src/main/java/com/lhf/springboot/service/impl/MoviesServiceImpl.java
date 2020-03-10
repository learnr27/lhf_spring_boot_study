package com.lhf.springboot.service.impl;

import com.lhf.springboot.entity.Movies;
import com.lhf.springboot.mapper.MoviesMapper;
import com.lhf.springboot.service.MoviesService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: MoviesServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 14:55
 */
@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private MoviesMapper moviesMapper;

    @Override
    public void addMovies(Movies movies) {
        moviesMapper.addMovies(movies);
    }

    @Override
    public void updateMovies(Movies movies) {
        moviesMapper.updateMovies(movies);
    }

    @Override
    public void deleteByMovieCode(String movieCode) {
        moviesMapper.deleteByMovieCode(movieCode);
    }

    @Override
    public List<Movies> findByAll() {
        return moviesMapper.findByAll();
    }

    @Override
    public Movies findByMovieCode(String movieCode) {
        return moviesMapper.findByMovieCode(movieCode);
    }

    @Override
    public Movies findByMovieName(String movieName) {
        return moviesMapper.findByMovieName(movieName);
    }
}

