package com.lhf.springboot.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ClassName: A
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 15:52
 */
@Data
@Entity
public class Aa {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String SeatPosition;
}
