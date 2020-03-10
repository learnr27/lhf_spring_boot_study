package com.lhf.springboot.number;

import java.text.NumberFormat;

/**
 * @ClassName: NumUtil
 * @Author: liuhefei
 * @Description: 数字填充工具类
 * @Date: 2019/10/10 11:58
 */
public class NumUtil {

    /**
     * description: 格式化数字，实现左侧补 0.
     *
     * @param num 格式化的数字
     * @param min 最小位数
     * @param max 最大位数
     * @return String
     * @version v1.0
     * @author w
     * @date 2019年10月10日
     */
    public static String fill(int num, int min, int max) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 禁用数字格式化分组。 如： 000,001
        numberFormat.setGroupingUsed(false);
        // 保留最小位数
        numberFormat.setMinimumIntegerDigits(min);
        // 保留最大位数
        numberFormat.setMaximumIntegerDigits(max);
        return numberFormat.format(num);
    }

    /**
     * description: 使用 String.format 格式化数字，实现左侧补 0
     *
     * @param num   需要格式化的数字
     * @param digit 生成字符串长度（保留数字位数）
     * @return String
     * @version v1.0
     * @author w
     * @date 2019年10月10日
     */
    public static String fillString(int num, int digit) {
        /**
         * 0：表示前面补0 digit：表示保留数字位数 d：表示参数为正数类型
         */
        return String.format("%0" + digit + "d", num);
    }

    public static void main(String[] args) {
        /**
         * 需求： 编码序号为 5 位数，不足部分左侧补 0 即： max = min =5
         */
        String fill = fill(1287, 8, 8);
        System.out.println(fill);
        fill = fill(233, 15, 15);
        System.out.println(fill);

        String fillStr = fillString(6543, 8);
        System.out.println("\n"+fillStr);
        fillStr = fillString(233, 8);
        System.out.println(fillStr);

    }

}
