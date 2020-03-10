package com.lhf.springboot.mapper;

import com.lhf.springboot.entity.Movies;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: MoviesMapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 11:57
 */

@Mapper
public interface MoviesMapper {

    @Insert("insert into movies(movie_name, movie_actor, long_time, movie_desc, show_time, box_office, movie_code) values( #{movieName}, #{movieActor}, #{longTime}, #{movieDesc}, #{showTime}, #{boxOffice}, #{movieCode})")
    void addMovies(Movies movies);


    @Update("update movies set movie_name = #{movieName}, movie_actor = #{movieActor}, long_time = #{longTime}, movie_desc = #{movieDesc}, show_time = #{showTime}, box_office = #{boxOffice} where movie_code = #{movieCode}")
    void updateMovies(Movies movies);

    @Delete("delete from movies where movie_code = #{movieCode")
    void deleteByMovieCode(String movieCode);

    @Select("select * from movies ")
    List<Movies> findByAll();


    @Select("select * from movies where movie_code = #{movieCode")
    Movies findByMovieCode(String movieCode);

    @Select("select * from movies where movie_name like '%#{movieName}%' ")
    Movies findByMovieName(String movieName);


}
