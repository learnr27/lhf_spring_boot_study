package com.lhf.springboot.benchmark.sum.calc;

/**
 * @ClassName: Calculator
 * @Author: liuhefei
 * @Description: 计算接口
 * @Date: 2019/7/23 10:37
 */
public interface Calculator {

    /**
     * 计算整数数组的和
     * @param numbers
     * @return
     */
    public long sum(int[] numbers);

    /**
     * 关闭资源
     */
    public void shutdown();

}
