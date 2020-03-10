package com.lhf.springboot.echarts.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BaseResponse
 * @Author: liuhefei
 * @Description: API接口的基础返回类
 * @Date: 2019/7/23 14:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    //是否成功
    private boolean success;

    //说明
    private String msg;

    //返回数据
    private T data;


}
