package com.lhf.springboot.benchmark.sum.calc.impl;

import com.lhf.springboot.benchmark.sum.calc.Calculator;
import sun.awt.SunToolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName: MultithreadCalculator
 * @Author: liuhefei
 * @Description: 多线程求和
 * @Date: 2019/7/23 10:42
 */
public class MultithreadCalculator implements Calculator {
    private final int nThreads;
    private final ExecutorService pool;  //线程池

    public MultithreadCalculator(int nThreads) {
        this.nThreads = nThreads;
        this.pool = Executors.newFixedThreadPool(nThreads);
    }

    private class SumTask implements Callable<Long>{
        private int[] numbers;
        private int from;
        private int to;

        public SumTask(int[] numbers, int from, int to){
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        public Long call() throws Exception{
            long total = 0L;
            for(int i = from; i < to;i++){
                total += numbers[i];
            }
            return total;
        }
    }


    @Override
    public long sum(int[] numbers) {
        int chunk = numbers.length / nThreads;
        int from, to;
        List<SumTask> tasks = new ArrayList<SumTask>();
        for(int i = 1; i <= nThreads;i++){
            if(i == nThreads){
                from = (i - 1)*chunk;
                to = numbers.length;
            }else {
                from = (i - 1)*chunk;
                to = i * chunk;
            }
            tasks.add(new SumTask(numbers, from, to));
        }

        try{
            List<Future<Long>> futures = pool.invokeAll(tasks);
            long total = 0L;
            for(Future<Long> future : futures){
                total += future.get();
            }
            return total;
        }catch (Exception e){

            return 0;
        }
    }

    @Override
    public void shutdown() {
        pool.shutdown();

    }
}
