package com.lhf.springboot.test;

import com.alibaba.fastjson.JSONObject;
import com.lhf.springboot.json.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: JSONTest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/6 14:36
 */
public class JSONTest {

    public static void main(String[] args) {

        Map<String, Object> map1 = new HashMap<>();
        map1.put("staffId", "00001234");
        map1.put("reportCode", "StaffPictureBaseInfo");
        map1.put("columnName", "Age");
        map1.put("searchValue", "23;26");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("columnName", "LastHireDate");
        map2.put("searchValue", "2015-09-01;2016-09-01");

        List<Map> mapList = new ArrayList<>();
        mapList.add(map1);
        mapList.add(map2);


        System.out.println("mapList = " + mapList);

        String params = "{\"staffId\":\"00001234\",\"staffName\":\"豆豆\",\"orgName\":\"销售部\",\"reportCode\":\"StaffPictureBaseInfo\",\"columnName\":[\"Age\"],\"condition\":[{\"columnName\":\"OrganizationID\",\"searchValue\":\"00004791\"},{\"columnName\":\"LastHireDate\",\"searchValue\":\"2015-09-01;2016-09-01\"}]}";
        Map<String, Object> strMapParam = (Map<String, Object>) JSONObject.parse(params);
        System.out.println("strMapParam = " + strMapParam);
        for (Map.Entry<String, Object> entry : strMapParam.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        List<Map> mapListstr = (List<Map>) strMapParam.get("condition");
        System.out.println("mapListstr = " + mapListstr);

        Map<String, Object> maps = new HashMap<>();
        maps.put("columnName", "StaffTypeName");
        maps.put("searchValue", "正式;顾问;毕业生");
        mapListstr.add(maps);
        System.out.println("mapListstr = " + mapListstr);

        strMapParam.put("condition", mapListstr);
        System.out.println("strMapParam111 = " + strMapParam);

        String object = JsonUtil.toJson(strMapParam);
        System.out.println("object = " + object);

        JSONObject json = JSONObject.parseObject(object);
        System.out.println("json = " + json);
    }
}
