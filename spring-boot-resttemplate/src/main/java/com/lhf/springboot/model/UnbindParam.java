package com.lhf.springboot.model;

import lombok.Data;

/**
 * @ClassName: UnbindParam
 * @Author: liuhefei
 * @Description: 解绑通知参数
 * @Date: 2019/7/31 14:44
 */
@Data
public class UnbindParam {

    /**
     * IMEI码
     */
    private String imei;
    /**
     * 网点
     */
    private String location;

}
