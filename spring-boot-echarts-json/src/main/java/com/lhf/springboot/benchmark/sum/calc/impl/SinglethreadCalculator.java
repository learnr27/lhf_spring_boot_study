package com.lhf.springboot.benchmark.sum.calc.impl;

import com.lhf.springboot.benchmark.sum.calc.Calculator;

/**
 * @ClassName: SinglethreadCalculator
 * @Author: liuhefei
 * @Description: 单线程求和
 * @Date: 2019/7/23 10:40
 */
public class SinglethreadCalculator implements Calculator {
    @Override
    public long sum(int[] numbers) {
        long total = 0L;
        for(int i : numbers){
            total += i;
        }
        return total;
    }

    @Override
    public void shutdown() {

    }
}
