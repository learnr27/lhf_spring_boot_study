package com.lhf.springboot.String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: MapStringUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/27 13:21
 */
public class MapStringUtil {

    /**
     *
     * map转string
     * @param map
     * @return
     */
    public static String getMapToString(Map<String, String> map){
        Set<String> keySet = map.keySet();
        //将set集合转换为数组
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        //给数组排序(升序)
        Arrays.sort(keyArray);
        //因为String拼接效率会很低的，所以转用StringBuilder。博主会在这篇博文发后不久，会更新一篇String与StringBuilder开发时的抉择的博文。
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            // 参数值为空，则不参与签名 这个方法trim()是去空格
            if (map.get(keyArray[i]).trim().length() > 0) {
                sb.append(keyArray[i]).append("=").append(map.get(keyArray[i]).trim());
            }
            if(i != keyArray.length-1){
                sb.append("&");
            }
        }
        return sb.toString();
    }

    /**
     * String转map
     * @param str
     * @return
     */
    public static Map<String,String> getStringToMap(String str){
        //感谢bojueyou指出的问题
        //判断str是否有值
        if(null == str || "".equals(str)){
            return null;
        }
        //根据&截取
        String[] strings = str.split("&");
        //设置HashMap长度
        int mapLength = strings.length;
        //判断hashMap的长度是否是2的幂。
        if((strings.length % 2) != 0){
            mapLength = mapLength+1;
        }

        Map<String,String> map = new HashMap<>(mapLength);
        //循环加入map集合
        for (int i = 0; i < strings.length; i++) {
            //截取一组字符串
            String[] strArray = strings[i].split("=");
            //strArray[0]为KEY  strArray[1]为值
            map.put(strArray[0],strArray[1]);
        }
        return map;
    }

    public static void main(String[] args) {
        Map<String,String> objectMap = new HashMap<>(3);
        objectMap.put("id","1");
        objectMap.put("name","哆啦A梦丶幻想");
        //map转string
        String passBackParams = getMapToString(objectMap);
        System.out.println(passBackParams);
        try {
            //string转map
            Map map =  getStringToMap(passBackParams);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
