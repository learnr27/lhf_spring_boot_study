package com.lhf.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BaseResponse
 * @Author: liuhefei
 * @Description: Controller的基础返回类
 * @Date: 2019/7/31 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 描述说明
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;


}
