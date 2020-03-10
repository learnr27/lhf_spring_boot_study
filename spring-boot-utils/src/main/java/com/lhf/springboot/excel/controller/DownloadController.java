package com.lhf.springboot.excel.controller;

import com.lhf.springboot.excel.utils.ExcelUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: DownloadController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/11/22 15:18
 */
@RestController
public class DownloadController {

    //http://localhost:9095/dawnload
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadExcel(HttpServletResponse response){
        try{
            String fileName = "用户信息";
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Map<String, String> map = new HashMap<>();
            map.put("name", "刘豆豆");
            map.put("age", "12");

            List<Map<String,String>> data = new ArrayList<>();
            data.add(map);
            List<Map> result = data.stream().collect(Collectors.toList());
            outputStream = ExcelUtil.exportTableDataExcel(result);
            response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName+".xlsx","UTF-8")+"" );
            response.setContentType("multipart/form-data;charset=UTF-8");
            Integer contentLength = outputStream.size();
            response.setHeader("content-length", new Long(contentLength).intValue() + "");
            response.getOutputStream().write(outputStream.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
