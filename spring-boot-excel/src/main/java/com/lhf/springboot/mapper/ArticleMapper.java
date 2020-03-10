package com.lhf.springboot.mapper;

import com.lhf.springboot.entity.po.ArticlePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ArticleMapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/8 15:39
 */
@Mapper
public interface ArticleMapper {

    List<ArticlePo> articleList(Map<String, Object> map);
}
