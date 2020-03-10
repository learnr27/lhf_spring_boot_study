package com.lhf.springboot.controller;

import com.lhf.springboot.common.JsonResult;
import com.lhf.springboot.model.Article;
import com.lhf.springboot.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName: ArticleController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/9 10:24
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/list.query", method = RequestMethod.GET)
    @ResponseBody
    public Object findByAll(){
        List<Article> articleList = articleService.findArticleByAll(1);
        logger.info("articleList = {}", articleList);
        JsonResult jsonResult = new JsonResult(articleList);
        jsonResult.setStatus(0);
        jsonResult.setData(articleList);
        jsonResult.setErrMsg("SUCCESS");
        return jsonResult;
    }

    @RequestMapping(value = "/articleConnection.query", method = {RequestMethod.GET})
    @ResponseBody
    public Object articleConnection(@RequestParam(name = "title", required = true) String title,
                                   @RequestParam(name = "author", required = false) String author) {
        List<Article> article = articleService.findArticleByTitleOrAuthor(title, author);
        logger.info("article = {}", article);
        return article;
    }

    @RequestMapping(value = "/create.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Article> addArticle(@RequestBody Article article){
        article.setStatus(1);
        Article articleData = articleService.addArticle(article);
        System.out.println("articleData = " + articleData);
        return new JsonResult(1, articleData, "SUCCESS");
    }


    @RequestMapping(value = "/update.do", method = RequestMethod.GET)
    @ResponseBody
    public Object updateArticle(@RequestParam Integer id) {

        return articleService.updateArticle(id);
    }

    @RequestMapping(value = "/deleteArticle.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteArticle(@RequestParam(value = "id", required = true) Integer id){
        boolean bool = articleService.deleteArticle(id);
        if(bool){
            return new JsonResult(1,"删除成功！");
        }else {
            return new JsonResult(-1, "删除失败！");
        }

    }


}
