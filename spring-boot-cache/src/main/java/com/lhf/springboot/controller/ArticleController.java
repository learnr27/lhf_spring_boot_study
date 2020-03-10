package com.lhf.springboot.controller;

import com.lhf.springboot.domain.Article;
import com.lhf.springboot.service.ArticleService;
import com.lhf.springboot.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.AnnotatedType;
import java.util.List;

/**
 * @ClassName: ArticleController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/12 14:18
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 默认为ConcurrenMapCacheManager
     */
    @Autowired
    private CacheManager cacheManager;

    @PostMapping("/add")
    public String addArticle(@RequestBody Article article){
        articleService.addArticle(article);
        return "success";
    }

    @GetMapping(value = "/findAll")
    public List<Article> articleList(){
        return articleService.articleList();
    }

    @GetMapping(value = "/getArticle")
    public Article findArticleById(@RequestParam(value = "id", required = true) Integer id){
        Article article = articleService.findArticleById(id);
        return article;
    }

    @DeleteMapping("/delete")
    public Object deleteArticle(@RequestParam(value = "id", required = true)Integer id){
        articleService.deleteArticle(id);
        return "Success";
    }

    @PostMapping("/update")
    public String updateArticle(@RequestBody Article article){
        article.setId(article.getId());
        article.setAuthor(article.getAuthor());
        article.setTitle(article.getTitle());
        article.setContent(article.getContent());

        articleService.updateArticle(article);

        return "success";
    }
}
