package com.lhf.springboot.controller;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName: TestMain
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/18 16:58
 */
public class TestMain {

    public static void main(String[] args) {
        String str = "a,b,c,d,e,f";

        ArrayList<String> list = new ArrayList<String>();
        list.add(str);

        String[] arr = list.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));



    }
}
