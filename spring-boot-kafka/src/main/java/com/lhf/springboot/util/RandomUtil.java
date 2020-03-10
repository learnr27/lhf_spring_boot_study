package com.lhf.springboot.util;

import java.util.Random;

/**
 * @ClassName: RandomUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/12 15:36
 */
public class RandomUtil {

    public static String getRandomString(int length){
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="用我三生烟火，换你一世迷离。长街长，烟花繁，你挑灯回看，短亭短，红尘辗，我把萧再叹。终是谁使弦断，花落肩头，恍惚迷离多少红颜悴，多少相思碎，唯留血染墨香哭乱冢。苍茫大地一剑尽挽破，何处繁华笙歌落。斜倚云端千壶掩寂寞，纵使他人空笑我。任他凡事清浊，为你一笑间轮回甘堕。寄君一曲，不问曲终人聚散。谁将烟焚散，散了纵横的牵绊。听弦断，断那三千痴缠。坠花湮，湮没一朝风涟。花若怜，落在谁的指尖。灯火星星，人声杳杳，歌不尽乱世烽火。如花美眷，似水流年，回得了过去，回不了当初。似此星辰非昨夜，为谁风露立中宵。蝴蝶很美，终究蝴蝶飞不过沧海。山河拱手，为君一笑 。待浮花浪蕊俱尽，伴君幽独。";
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
}
