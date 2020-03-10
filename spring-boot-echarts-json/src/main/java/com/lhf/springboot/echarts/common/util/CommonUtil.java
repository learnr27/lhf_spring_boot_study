package com.lhf.springboot.echarts.common.util;

/**
 * @ClassName: CommonUtil
 * @Author: liuhefei
 * @Description: 常用工具类，字符串，数字相关
 * @Date: 2019/7/23 15:04
 */
public class CommonUtil {

    /**
     * 检查某版本是否比现在版本更大些
     *
     * @param version    某版本
     * @param nowVersion 现在使用的版本
     * @return 是否版本数更大
     */
    public static boolean isNewer(String version, String nowVersion) {
        try {
            String[] versions = version.split("\\.");
            String[] nowVersions = nowVersion.split("\\.");

            if (versions.length != nowVersions.length) {
                return false;
            }
            int sum = 0;
            for (String v : versions) {
                sum += sum * 10 + Integer.valueOf(v);
            }

            int nowSum = 0;
            for (String nv : nowVersions) {
                nowSum += nowSum * 10 + Integer.valueOf(nv);
            }

            return sum > nowSum;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
