package com.lhf.springboot.mapper;

import com.lhf.springboot.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: ArticleMapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/12 12:27
 */
@Mapper
public interface ArticleMapper {

    List<Article> findArticleList();

    Article findArticleById(final Integer id);

    void deleteArticle(final Integer id);

    void updateArticle(final Article article);

    void addArticle(Article article);
}
