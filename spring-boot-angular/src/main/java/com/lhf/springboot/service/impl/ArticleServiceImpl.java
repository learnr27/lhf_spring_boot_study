package com.lhf.springboot.service.impl;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lhf.springboot.dao.ArticleRepository;
import com.lhf.springboot.model.Article;
import com.lhf.springboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Throwables.propagate;

/**
 * @ClassName: ArticleServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/9 10:22
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> findByAll() {

        return articleRepository.findAll();
    }

    @Override
    public List<Article> findArticleByAll(Integer status) {
        return articleRepository.findArticleByStatusEquals(status);
    }

    @Override
    public List<Article> findArticleByTitleOrAuthor(@Param(value = "title") String title, @Param(value = "author") String author) {
        List<Article> articleList = articleRepository.findArticleByStatusAndTitleOrAuthor(1, title, author);

        return articleList;
    }



    @Override
    public Article addArticle(Article article) {
        return articleRepository.saveAndFlush(article);
    }

    @Override
    public List<Article> updateArticle(Integer id) {

        Article article = articleRepository.findArticleByIdAndStatusEquals(id, 1);
        List<Article> articleAllList = Lists.newArrayList();
        try{
            articleAllList.add(new Article(id, article.getTitle(), article.getAuthor(), article.getType(), article.getContent(), article.getPress(),  article.getPubDate(), 1));

        }catch (Exception e){
            throw new RuntimeException("发送了异常");
        }

        return articleAllList;
    }


    @Override
    public boolean deleteArticle(Integer id) {
       try {
           Article article = articleRepository.findArticleByIdAndStatusEquals(id, 1);
           if(article != null){
               article.setStatus(-1);
               articleRepository.saveAndFlush(article);
           }
           return true;
       }catch (Exception e){
           return false;
       }
    }
}
