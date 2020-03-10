package com.lhf.springboot.dao;

import com.lhf.springboot.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: ArticleRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/9 10:17
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findArticleByStatusEquals(int status);

    List<Article> findArticleByStatusAndTitleOrAuthor(Integer status,String title, String author);

    /*@Modifying   //SELECT * FROM article WHERE `status` = 1 AND title like '%国%' OR author like '%吴%';
    @Query("SELECT a.id,a.title,a.author,a.content,a.type,a.press,a.pubDate FROM Article a WHERE a.status='1' AND a.title = ?1 OR a.author = ?2")
    Integer findArticleByTitleOrAuthorEquals(String title, String author);*/

    /*@Modifying   //SELECT * FROM article WHERE `status` = 1 AND title like '%国%' OR author like '%吴%';
    @Query("SELECT a.id,a.title,a.author,a.content,a.type,a.press,a.pubDate FROM Article a WHERE a.status='1' AND a.title like ?1")
    Integer findArticleByTitleLike(String title);*/

    Article findArticleByIdAndStatusEquals(Integer id, Integer status);

}
