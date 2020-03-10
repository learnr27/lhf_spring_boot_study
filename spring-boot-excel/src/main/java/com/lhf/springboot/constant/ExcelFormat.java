package com.lhf.springboot.constant;

/**
 * @ClassName: ExcelFormat
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/8 14:53
 */
public enum ExcelFormat {   //默认为字符串类型
    FORMAT_INTEGER("INTEGER"),    //整型
    FORMAT_DOUBLE("DOUBLE"),    //Double
    FORMAT_PERCENT("PERCENT"),  //Percent
    FORMAT_DATE("DATE");   //Date

    private String value;

    ExcelFormat(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
