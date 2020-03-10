package com.lhf.springboot.algorithm;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @ClassName: BloomFilterDemo
 * @Author: liuhefei
 * @Description: 布隆过滤器
 * @Date: 2019/12/3 10:46
 */
public class BloomFilterDemo {

    public static void main(String[] args) {
        int total = 1000000;  //总数量


        //创建布隆过滤器 不设置误判率
        //BloomFilter<CharSequence> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);
        //创建布隆过滤器 设置误判率
        BloomFilter<CharSequence> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total, 0.0002);


        //初始化1000000条数据到过滤器中
        for(int i = 0; i< total;i++){
            bf.put(""+i);
        }

        //判断值是否存在于过滤器中
        int count = 0;
        for(int i = 0;i < total + 10000; i++ ){
            if(bf.mightContain("" + i)){
                count ++;
            }
        }
        System.out.println("已匹配的数量：" + count);    //1000309

        //误判率为：309/(1000000 + 10000) * 100 ≈ 0.030594059405940593

    }
}
