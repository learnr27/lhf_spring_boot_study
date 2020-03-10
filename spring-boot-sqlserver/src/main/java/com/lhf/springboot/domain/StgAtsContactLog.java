package com.lhf.springboot.domain;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: StgAtsContactlog
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/6 17:08
 */
@Data
@Entity
@Table(name = "STG_ATS_CONTACTLOG", schema = "STG")  //name设置表名，schema设置数据库模式名
public class StgAtsContactLog {

    @Id
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "uuidGenerator")
    @Column(name = "_ID")
    private String id;

    @Column(name = "USERID")
    private Integer userId;

    @Column(name = "EMPLOYEEID")
    private Integer employeeId;

    @Column(name = "WEB")
    private String web;

    @Column(name = "API")
    private String api;

    @Column(name = "HOST")
    private String host;

    @Column(name = "INPUTTIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.zzz", timezone="GMT+8")
    private Date inputTime;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "MSG")
    private String msg;

    @Column(name = "USERAGENT")
    private String userAgent;

    @Column(name = "REFER")
    private String refer;

    @Column(name = "BEFOREFAKEUSERID")
    private Integer beforeFakeUserId;

    @Column(name = "__AUDIT_CREATEDON")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.zzz", timezone="GMT+8")
    private Date auditCreatedon;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
