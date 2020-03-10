package com.lhf.springboot.algorithm;

import java.util.BitSet;

/**
 * @ClassName: SimpleBloomFilter
 * @Author: liuhefei
 * @Description: 简易布隆过滤器
 *
 * 我们使用到了 Java util 包中的 BitSet，BitSet 是位操作的对象，值只有 0 或 1 ，内部维护了一个 long 数组，初始只有一个 long，所以 BitSet 最小的容量是 64 位。
 * 当随着存储的元素越来越多，BitSet 内部会动态扩容，最终内部是由 N 个 long 值来存储。默认情况下，BitSet 的所有位都是 0。
 *
 * @Date: 2019/12/3 10:56
 */
public class SimpleBloomFilter {

    private static final int DEFAULT_SIZE = 2 << 24;
    private static final int[] seeds = new int[]{7, 11, 13, 31, 37, 61};

    private BitSet bits = new BitSet(DEFAULT_SIZE);
    private SimpleHash[] func = new SimpleHash[seeds.length];

    public SimpleBloomFilter() {
        // 创建多个哈希函数
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    /**
     * 添加元素到布隆过滤器中
     *
     * @param value
     */
    public void put(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断布隆过滤器中是否包含指定元素
     *
     * @param value
     * @return
     */
    public boolean mightContain(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 简单哈希类
     */
    public static class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (cap - 1) & result;
        }
    }

    public static void main(String[] args) {
        SimpleBloomFilter bf = new SimpleBloomFilter();
        for (int i = 0; i < 1000000; i++) {
            bf.put("" + i);
        }
        // 判断值是否存在过滤器中
        int count = 0;
        for (int i = 0; i < 1000000 + 10000; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }
        System.out.println("已匹配数量 " + count);
    }

}
