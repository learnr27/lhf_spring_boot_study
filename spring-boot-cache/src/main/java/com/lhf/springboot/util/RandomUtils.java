package com.lhf.springboot.util;

import java.util.Random;

/**
 * @ClassName: RandomUtils
 * @Desc: 生成随机字符串
 * @Author: liuhefei
 * @Date: 2019/2/28 14:16
 */
public class RandomUtils {
    public static String getRandomString(int length){
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从62个的数字或字母中选择
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    public static String getRandomNum(int length){
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="1234567890";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从10个的数字中选择
            int number=random.nextInt(10);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }


    public static String getRandomContent(int length){
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="夜结束了一天的喧嚣后安静下伴随着远处路灯那微弱的光风毫无预兆地席卷整片旷野撩动人的思绪万千星遥遥地挂在天空之中闪烁着它那微微星光不如阳光般灿烂却如花儿般如痴如醉";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从62个的数字或字母中选择
            int number=random.nextInt(str.length());
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }



    public static void main(String[] args) {
        //这里的32是生成32位随机码，根据你的需求，自定义
        String random1 = getRandomString(32);
        System.out.println(random1);
        String random2 = getRandomNum(32);
        System.out.println(random2);
    }

}
