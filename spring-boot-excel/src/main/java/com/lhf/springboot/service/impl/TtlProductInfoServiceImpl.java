package com.lhf.springboot.service.impl;


import com.lhf.springboot.constant.ExcelFormat;
import com.lhf.springboot.entity.ExcelHeaderInfo;
import com.lhf.springboot.entity.po.TtlProductInfoPo;
import com.lhf.springboot.mapper.TtlProductInfoMapper;
import com.lhf.springboot.service.TtlProductInfoService;
import com.lhf.springboot.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author kundy
 * @create 2019/2/16 11:22 AM
 */
@Service
@Slf4j
public class TtlProductInfoServiceImpl implements TtlProductInfoService {

    // 每个线程导出记录最大行数
    private static final int THREAD_MAX_ROW = 20000;

    @Autowired
    private TtlProductInfoMapper mapper;

    @Override
    public List<TtlProductInfoPo> listProduct(Map<String, Object> map) {
        return this.mapper.listProduct(map);
    }

    @Override
    public void export(HttpServletResponse response, String fileName) {
        // 待导出数据
        List<TtlProductInfoPo> productInfoPos = this.multiThreadListProduct();
        ExcelUtils excelUtils = new ExcelUtils(productInfoPos, getHeaderInfo(), getFormatInfo());
        excelUtils.sendHttpResponse(response, fileName, excelUtils.getWorkbook());
    }

    // 获取表头信息
    private List<ExcelHeaderInfo> getHeaderInfo() {
        return Arrays.asList(
                new ExcelHeaderInfo(1, 1, 0, 0, "id"),
                new ExcelHeaderInfo(1, 1, 1, 1, "商品名称"),

                new ExcelHeaderInfo(0, 0, 2, 3, "分类"),
                new ExcelHeaderInfo(1, 1, 2, 2, "类型ID"),
                new ExcelHeaderInfo(1, 1, 3, 3, "分类名称"),

                new ExcelHeaderInfo(0, 0, 4, 5, "品牌"),
                new ExcelHeaderInfo(1, 1, 4, 4, "品牌ID"),
                new ExcelHeaderInfo(1, 1, 5, 5, "品牌名称"),

                new ExcelHeaderInfo(0, 0, 6, 7, "商店"),
                new ExcelHeaderInfo(1, 1, 6, 6, "商店ID"),
                new ExcelHeaderInfo(1, 1, 7, 7, "商店名称"),

                new ExcelHeaderInfo(1, 1, 8, 8, "价格"),
                new ExcelHeaderInfo(1, 1, 9, 9, "库存"),
                new ExcelHeaderInfo(1, 1, 10, 10, "销量"),
                new ExcelHeaderInfo(1, 1, 11, 11, "插入时间"),
                new ExcelHeaderInfo(1, 1, 12, 12, "更新时间"),
                new ExcelHeaderInfo(1, 1, 13, 13, "记录是否已经删除")
        );
    }

    // 获取格式化信息
    private Map<String, ExcelFormat> getFormatInfo() {
        Map<String, ExcelFormat> format = new HashMap<>();
        format.put("id", ExcelFormat.FORMAT_INTEGER);
        format.put("categoryId", ExcelFormat.FORMAT_INTEGER);
        format.put("branchId", ExcelFormat.FORMAT_INTEGER);
        format.put("shopId", ExcelFormat.FORMAT_INTEGER);
        format.put("price", ExcelFormat.FORMAT_DOUBLE);
        format.put("stock", ExcelFormat.FORMAT_INTEGER);
        format.put("salesNum", ExcelFormat.FORMAT_INTEGER);
        format.put("isDel", ExcelFormat.FORMAT_INTEGER);
        return format;
    }

    /**
     * 多线程查询报表
     * 因为多个线程之间是同时执行的，你不能够保证哪个线程先执行完毕，但是我们却得保证数据顺序的一致性。在这里我们使用了Callable接口，通过实现Callable接口的线程可以拥有返回值，我们获取到所有子线程的查询结果，然后合并到一个结果集中即可。
     * 那么如何保证合并的顺序呢?我们先创建了一个FutureTask类型的List，该FutureTask的类型就是返回的结果集。
     * @return
     */
    private List<TtlProductInfoPo> multiThreadListProduct() {
        List<FutureTask<List<TtlProductInfoPo>>> tasks = new ArrayList<>();
        List<TtlProductInfoPo> productInfoPos = new ArrayList<>();

        int totalNum = 500000;
        int loopNum = new Double(Math.ceil((double) totalNum / THREAD_MAX_ROW)).intValue();
        log.info("多线程查询，总数：{},开启线程数：{}", totalNum, loopNum);
        long start = System.currentTimeMillis();

        executeTask(tasks, loopNum, totalNum);

        //我们按顺序从tasks列表中取出FutureTask，然后执行FutureTask的get()方法，该方法会阻塞调用它的线程，知道拿到返回结果。这样一套循环下来，就完成了所有数据的按顺序存储。
        for (FutureTask<List<TtlProductInfoPo>> task : tasks) {
            try {
                productInfoPos.addAll(task.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        log.info("查询结束，耗时:{}", System.currentTimeMillis() - start);
        return productInfoPos;
    }

    // 执行查询任务
    private void executeTask(List<FutureTask<List<TtlProductInfoPo>>> tasks, int loopNum, int total) {
        for (int i = 0; i < loopNum; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("offset", i * THREAD_MAX_ROW);
            if (i == loopNum - 1) {
                map.put("limit", total - THREAD_MAX_ROW * i);
            } else {
                map.put("limit", THREAD_MAX_ROW);
            }

            //当我们每启动一个线程的时候，就将该线程的FutureTask添加到tasks列表中，这样tasks列表中的元素顺序就是我们启动线程的顺序。
            FutureTask<List<TtlProductInfoPo>> task = new FutureTask<>(new listThread(map));
            log.info("开始查询第{}条开始的{}条记录", i * THREAD_MAX_ROW, THREAD_MAX_ROW);
            new Thread(task).start();
            // 将任务添加到tasks列表中
            tasks.add(task);
        }
    }

    private class listThread implements Callable<List<TtlProductInfoPo>> {

        private Map<String, Object> map;

        private listThread(Map<String, Object> map) {
            this.map = map;
        }

        @Override
        public List<TtlProductInfoPo> call() {
            return listProduct(map);
        }
    }

}
