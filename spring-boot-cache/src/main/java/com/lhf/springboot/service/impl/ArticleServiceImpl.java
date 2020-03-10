package com.lhf.springboot.service.impl;

import com.lhf.springboot.domain.Article;
import com.lhf.springboot.mapper.ArticleMapper;
import com.lhf.springboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ArticleServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/12 12:29
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    //使用ehcache配置的缓存名 article_test
    private final String ARTICLE_CACHE_NAME = "article_test";

    @Override
    //@Cacheable(value = ARTICLE_CACHE_NAME, key="#id")
    @Cacheable(value = ARTICLE_CACHE_NAME,key=" 'article' + #id" )
    public List<Article> articleList() {
        return articleMapper.findArticleList();
    }

    @Override
    //@Cacheable(value = ARTICLE_CACHE_NAME, key="#id")
    @Cacheable(value = ARTICLE_CACHE_NAME,key=" 'article_' + #id" )
    public Article findArticleById(Integer id) {
        return articleMapper.findArticleById(id);
    }

    @Override
    //@CacheEvict(value = ARTICLE_CACHE_NAME, key="#id")
    @CacheEvict(value = ARTICLE_CACHE_NAME,key=" 'article_del_' + #id" )
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }

    @Override
    //@CacheEvict(value = ARTICLE_CACHE_NAME, key="#article.id")
    @CacheEvict(value = ARTICLE_CACHE_NAME,key=" 'article_update_' + #article.id" )
    //@CachePut(value = ARTICLE_CACHE_NAME, key = " 'article' + #article.id" ) //测试发现只将结果清除，key未清除，导致查询继续使用缓存但结果为空
    public void updateArticle(Article article) {
         articleMapper.updateArticle(article);
    }

    @Override
    //@CacheEvict(value = ARTICLE_CACHE_NAME, key="#article.id")
    @CacheEvict(value = ARTICLE_CACHE_NAME,key=" 'article_add_' + #article.id" )
    public void addArticle(Article article) {
        articleMapper.addArticle(article);
    }
}
