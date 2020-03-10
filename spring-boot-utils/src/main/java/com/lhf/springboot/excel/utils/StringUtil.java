package com.lhf.springboot.excel.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: StringUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/11/22 15:11
 */
public class StringUtil {

    public StringUtil() {
    }

    public static String join(Object[] array, String separator) {
        String join = StringUtils.join(array, separator);
        return join;
    }

    public static String join(Iterable<?> iterable, String separator) {
        return StringUtils.join(iterable.iterator(), separator);
    }

    public static String join(Object[] array) {
        String join = StringUtils.join(array, ",");
        return join;
    }

    public static String unCapitalize(String str) {
        return StringUtils.uncapitalize(str);
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    public static boolean equals(String str1, String str2) {
        return StringUtils.equals(str1, str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return StringUtils.equalsIgnoreCase(str1, str2);
    }

    public static boolean notEquals(String str1, String str2) {
        return !StringUtils.equals(str1, str2);
    }

    public static String fillAndSubstrToLen(char c, String str, int len) {
        int lens = str.length() - len;
        if (lens == 0) {
            return str;
        } else if (lens > 0) {
            return str.substring(lens);
        } else {
            StringBuilder sb = new StringBuilder();
            int length = -lens;

            for(int i = 0; i < length; ++i) {
                sb.append(c);
            }

            String ret = sb.toString() + str;
            return ret;
        }
    }

    public static String subUnderLine(String id) {
        if (id.length() > 31) {
            int indexOf = id.lastIndexOf("_");
            return indexOf > 0 ? id.substring(0, indexOf) : id;
        } else {
            return id;
        }
    }
}
