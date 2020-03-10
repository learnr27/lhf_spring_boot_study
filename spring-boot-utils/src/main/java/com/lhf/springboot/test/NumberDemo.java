package com.lhf.springboot.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @ClassName: NumberDemo
 * @Author: liuhefei
 * @Description: 保留2位小数
 * @Date: 2019/9/27 20:39
 */
public class NumberDemo {
    public static void main(String[] args) {
        double d = 756.2345566;
        //方法一：最简便的方法，调用DecimalFormat类
        DecimalFormat df = new DecimalFormat(".00");
        System.out.println(df.format(d));
        //方法二：直接通过String类的format函数实现
        System.out.println(String.format("%.2f", d));
        //方法三：通过BigDecimal类实现
        BigDecimal bg = new BigDecimal(d);
        double d3 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(d3);
        //方法四：通过NumberFormat类实现
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(d));
    }
}
