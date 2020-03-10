package com.lhf.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: Movies
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 11:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies implements Serializable {

    private static final long serialVersionUID = 5249500875744787597L;

    private Integer id;

    private String movieCode;  //电影上映序列码

    private String movieName;  //电影名称

    private String movieActor;  //主演

    private Integer longTime;  //电影时长/分钟

    private String movieDesc;  //电影描述

    private Date showTime;  //上映时间

    private BigDecimal boxOffice;  //票房/亿元
}
