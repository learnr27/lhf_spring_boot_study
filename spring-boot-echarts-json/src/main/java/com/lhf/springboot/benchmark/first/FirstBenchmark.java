package com.lhf.springboot.benchmark.first;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: FirstBenchmark
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 10:12
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class FirstBenchmark {

    @Benchmark
    public int sleepAWhile(){
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){

        }
        return 0;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FirstBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();
        new Runner(opt).run();
    }
}
