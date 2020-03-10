package com.lhf.springboot.service.impl;

import com.lhf.springboot.constant.ExcelFormat;
import com.lhf.springboot.entity.ExcelHeaderInfo;
import com.lhf.springboot.entity.po.ArticlePo;
import com.lhf.springboot.mapper.ArticleMapper;
import com.lhf.springboot.service.ArticleService;
import com.lhf.springboot.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: ArticleServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/8 16:15
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    //每个线程导出记录最大行数
    private static final int THREAD_MAX_ROW = 20000;

    @Autowired
    private ArticleMapper articleMapper;



    @Override
    public List<ArticlePo> articleList(Map<String, Object> map) {
        return this.articleMapper.articleList(map);
    }

    @Override
    public void export(HttpServletResponse response, String fileName) {
        //待导出数据
        List<ArticlePo> articlePos = this.multiThreadListArticle();
        ExcelUtils excelUtils = new ExcelUtils(articlePos, getHeaderInfo(), getFormatInfo());
        excelUtils.sendHttpResponse(response, fileName, excelUtils.getWorkbook());
    }

    //获取表头信息
    private List<ExcelHeaderInfo> getHeaderInfo(){
        return Arrays.asList(
                new ExcelHeaderInfo(0, 0, 0, 7, "图书名著"),  //表头

                new ExcelHeaderInfo(1, 1, 0, 0, "id"),
                new ExcelHeaderInfo(1, 1, 1, 1, "书名"),
                new ExcelHeaderInfo(1, 1, 2, 2, "作者"),
                new ExcelHeaderInfo(1, 1, 3, 3, "内容"),
                new ExcelHeaderInfo(1, 1, 4, 4, "出版社"),
                new ExcelHeaderInfo(1, 1, 5, 5, "出版时间"),
                new ExcelHeaderInfo(1, 1, 6, 6, "类型"),
                new ExcelHeaderInfo(1, 1, 7, 7, "状态")

                );
    }

    /**
     * 获取格式化信息
     * @return
     */
    private Map<String, ExcelFormat> getFormatInfo(){
        Map<String, ExcelFormat> format = new HashMap<>();
        format.put("id", ExcelFormat.FORMAT_INTEGER);
        format.put("status", ExcelFormat.FORMAT_INTEGER);
        return format;
    }

    //多线程查询报表
    // 多线程查询报表
    private List<ArticlePo> multiThreadListArticle() {
        List<FutureTask<List<ArticlePo>>> tasks = new ArrayList<>();
        List<ArticlePo> articlePos = new ArrayList<>();

        int totalNum = 500000;
        int loopNum = new Double(Math.ceil((double) totalNum / THREAD_MAX_ROW)).intValue();
        log.info("多线程查询，总数：{},开启线程数：{}", totalNum, loopNum);
        long start = System.currentTimeMillis();

        executeTask(tasks, loopNum, totalNum);

        for (FutureTask<List<ArticlePo>> task : tasks) {
            try {
                articlePos.addAll(task.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        log.info("查询结束，耗时:{}", System.currentTimeMillis() - start);
        return articlePos;
    }

    // 执行查询任务
    private void executeTask(List<FutureTask<List<ArticlePo>>> tasks, int loopNum, int total) {
        for (int i = 0; i < loopNum; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("offset", i * THREAD_MAX_ROW);
            if (i == loopNum - 1) {
                map.put("limit", total - THREAD_MAX_ROW * i);
            } else {
                map.put("limit", THREAD_MAX_ROW);
            }
            FutureTask<List<ArticlePo>> task = new FutureTask<>(new listThread(map));
            log.info("开始查询第{}条开始的{}条记录", i * THREAD_MAX_ROW, THREAD_MAX_ROW);
            new Thread(task).start();
            // 将任务添加到tasks列表中
            tasks.add(task);
        }
    }

    private class listThread implements Callable<List<ArticlePo>> {

        private Map<String, Object> map;

        private listThread(Map<String, Object> map) {
            this.map = map;
        }

        @Override
        public List<ArticlePo> call() {
            return articleList(map);
        }
    }

}
