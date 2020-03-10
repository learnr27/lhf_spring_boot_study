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

import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: JsonSerializeBenchmark
 * @Author: liuhefei
 * @Description: JSON序列化基准测试
 * @Date: 2019/7/23 11:03
 */
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class JsonSerializeBenchmark {

    /**
     * 序列化次数参数
     */
    @Param({"1000", "10000", "100000"})
    private int count;

    private Person p;

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(JsonSerializeBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(0)
                .build();
        Collection<RunResult> results = new Runner(opt).run();
        ResultExporter.exportResult("JSON序列化性能", results, "count", "秒");
    }

    @Benchmark
    public void JsonLib(){
        for(int i = 0;i<count;i++){
            JsonLibUtil.bean2Json(p);
        }
    }

    @Benchmark
    public void Gson(){
        for(int i = 0;i < count;i++){
            GsonUtil.bean2Json(p);
        }
    }

    @Benchmark
    public void FastJson(){
        for(int i = 0;i < count;i++){
            FastJsonUtil.bean2Json(p);
        }
    }

    @Benchmark
    public void Jackson(){
        for(int i = 0;i < count;i++){
            JsonLibUtil.bean2Json(p);
        }
    }

    @Setup
    public void prepare(){
        List<Person> friends = new ArrayList<Person>();
        friends.add(createAPerson("梦梦", null));
        friends.add(createAPerson("依依",null));
        friends.add(createAPerson("珊珊",null));
        p=createAPerson("天仙子",friends);
    }

    @TearDown
    public void shutdown(){

    }

    private Person createAPerson(String name, List<Person> friends){
        Person newPerson = new Person();
        newPerson.setName(name);
        newPerson.setFullName(new FullName("刘", "豆", "豆"));
        newPerson.setAge(24);
        List<String> hobbies = new ArrayList<>();
        hobbies.add("古筝");
        hobbies.add("游泳");
        hobbies.add("篮球");
        hobbies.add("旅行");
        newPerson.setHobbies(hobbies);
        Map<String, String> clothes = new HashMap<>();
        clothes.put("内衣", "美背文胸");
        clothes.put("外套", "阿迪达斯");
        clothes.put("裤子", "特步");
        clothes.put("鞋子", "安踏");
        newPerson.setClothes(clothes);
        newPerson.setFriends(friends);
        System.out.println("newPerson = " + newPerson);
        return newPerson;
    }

}
