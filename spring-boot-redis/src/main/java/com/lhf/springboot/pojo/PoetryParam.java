package com.lhf.springboot.pojo;

import com.lhf.springboot.model.Poetry;

/**
 * @ClassName: PoetryParam
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/30 16:46
 */
public class PoetryParam {

    Poetry[] poetry;

    public Poetry[] getPoetry() {
        return poetry;
    }

    public void setPoetry(Poetry[] poetry) {
        this.poetry = poetry;
    }
}
