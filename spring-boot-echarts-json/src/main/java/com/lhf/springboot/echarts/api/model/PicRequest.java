package com.lhf.springboot.echarts.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: PicRequest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 14:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicRequest {

    //Base64格式的图片
    private String picInfo;

}
