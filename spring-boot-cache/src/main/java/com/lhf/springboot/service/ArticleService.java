package com.lhf.springboot.service;

import com.lhf.springboot.domain.Article;

import java.util.List;

/**
 * @ClassName: ArticleService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/12 12:29
 */
public interface ArticleService {

    List<Article> articleList();

    Article findArticleById(final Integer id);

    void deleteArticle(final Integer id);

    void updateArticle(final Article article);

    void addArticle(final Article article);

}
