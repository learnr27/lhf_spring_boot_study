package com.lhf.springboot.service;

import com.lhf.springboot.model.Article;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ArticleService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/9 10:21
 */
public interface ArticleService {

    List<Article> findByAll();

    List<Article> findArticleByAll(Integer status);

    /**
     * 根据标题或作者查询文章
     * @param title
     * @param author
     * @return
     */
    List<Article> findArticleByTitleOrAuthor(String title, String author);


    /**
     * 添加文章
     * @param article
     */
    Article addArticle(Article article);

    List<Article> updateArticle(Integer id);

    boolean deleteArticle(Integer id);
}
