package com.lhf.springboot.benchmark.json;

import com.lhf.springboot.benchmark.common.ResultExporter;
import com.lhf.springboot.benchmark.json.model.FullName;
import com.lhf.springboot.benchmark.json.model.Person;
import com.lhf.springboot.benchmark.json.util.FastJsonUtil;
import com.lhf.springboot.benchmark.json.util.GsonUtil;
import com.lhf.springboot.benchmark.json.util.JsonLibUtil;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: JsonDeserializeBenchmark
 * @Author: liuhefei
 * @Description: JSON反序列化基准测试
 * @Date: 2019/7/23 11:03
 */
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class JsonDeserializeBenchmark {
    /**
     * 序列化次数参数
     */
    @Param({"1000", "10000", "100000"})
    private int count;

    private String jsonStr;

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(JsonSerializeBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(0)
                .build();
        Collection<RunResult> results = new Runner(opt).run();
        ResultExporter.exportResult("JSON反序列化性能", results, "count", "秒");
    }

    @Benchmark
    public void JsonLib(){
        for(int i = 0;i<count;i++){
            JsonLibUtil.json2Bean(jsonStr, Person.class);
        }
    }

    @Benchmark
    public void Gson(){
        for(int i = 0;i < count;i++){
            GsonUtil.json2Bean(jsonStr, Person.class);
        }
    }

    @Benchmark
    public void FastJson(){
        for(int i = 0;i < count;i++){
            FastJsonUtil.json2Bean(jsonStr, Person.class);
        }
    }

    @Benchmark
    public void Jackson(){
        for(int i = 0;i < count;i++){
            JsonLibUtil.json2Bean(jsonStr, Person.class);
        }
    }

    @Setup
    public void prepare(){
        jsonStr="{\"name\":\"天仙子\",\"fullName\":{\"firstName\":\"花\",\"middleName\":\"中\",\"lastName\":\"仙\"},\"age\":24,\"birthday\": null,\"hobbies\":[\"古筝\",\"篮球\",\"游泳\",\"旅行\"],\"clothes\":{\"内衣\":\"美背文胸\",\"外套\":\"阿迪达斯\",\"裤子\":\"特步\", \"鞋子\":\"安踏\"},\"friends\":[{\"name\":\"梦梦\",\"fullName\":{\"firstName\":\"xxx_first\",\"middleName\":\"xxx_middle\",\"lastName\":\"xxx_last\"},\"age\":24,\"birthday\":null,\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"clothes\":{\"shoes\":\"安踏\",\"trousers\":\"adidas\",\"coat\":\"Nike\"},\"friends\":null},{\"name\":\"Tony\",\"fullName\":{\"firstName\":\"xxx_first\",\"middleName\":\"xxx_middle\",\"lastName\":\"xxx_last\"},\"age\":24,\"birthday\":null,\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"clothes\":{\"shoes\":\"安踏\",\"trousers\":\"adidas\",\"coat\":\"Nike\"},\"friends\":null},{\"name\":\"陈小二\",\"fullName\":{\"firstName\":\"xxx_first\",\"middleName\":\"xxx_middle\",\"lastName\":\"xxx_last\"},\"age\":24,\"birthday\":null,\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"clothes\":{\"shoes\":\"安踏\",\"trousers\":\"adidas\",\"coat\":\"Nike\"},\"friends\":null}]}";
    }

    @TearDown
    public void shutdown(){

    }

}
