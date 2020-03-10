package com.lhf.springboot.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @ClassName: ResultMap
 * @Author: liuhefei
 * @Description: 接口返回对象
 * @Date: 2019/5/24 15:43
 */
@Component
public class ResultMap extends HashMap<String, Object> {
    public ResultMap() {
    }

    public ResultMap success() {
        this.put("result", "success");
        return this;
    }

    public ResultMap fail() {
        this.put("result", "fail");
        return this;
    }

    public ResultMap code(int code) {
        this.put("code", code);
        return this;
    }

    public ResultMap message(Object message) {
        this.put("message", message);
        return this;
    }
}

