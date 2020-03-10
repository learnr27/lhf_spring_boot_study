package com.lhf.springboot.benchmark.sum;

import com.lhf.springboot.benchmark.common.ResultExporter;
import com.lhf.springboot.benchmark.sum.calc.Calculator;
import com.lhf.springboot.benchmark.sum.calc.impl.MultithreadCalculator;
import com.lhf.springboot.benchmark.sum.calc.impl.SinglethreadCalculator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ClassName: SecondBenchmark
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 10:25
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SecondBenchmark {

    @Param({"10000", "100000", "1000000"})
    private int length;

    private int[] numbers;

    private Calculator singleThreadCalc;
    private Calculator multiThreadCalc;

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(SecondBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(2)
                .build();
        Collection<RunResult> results = new Runner(opt).run();
        ResultExporter.exportResult("单线程与多线程求和性能", results, "length", "微妙");
    }

    @Benchmark
    public long singleThreadBench(){
        return singleThreadCalc.sum(numbers);
    }

    @Benchmark
    public long multiThreadBench(){
        return multiThreadCalc.sum(numbers);
    }

    @Setup
    public void prepare(){
        numbers = IntStream.rangeClosed(1, length).toArray();
        singleThreadCalc = new SinglethreadCalculator();
        multiThreadCalc = new MultithreadCalculator(Runtime.getRuntime().availableProcessors());
    }

    @TearDown
    public void shutdown() {
        singleThreadCalc.shutdown();
        multiThreadCalc.shutdown();
    }
}
