package com.lhf.springboot.service;

import com.lhf.springboot.entity.po.ArticlePo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ArticleService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/8 16:15
 */
public interface ArticleService {

    List<ArticlePo> articleList(Map<String, Object> map);

    void export(HttpServletResponse response, String fileName);
}
