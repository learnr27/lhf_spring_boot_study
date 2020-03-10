package com.lhf.springboot.excel.utils;

import org.apache.commons.collections4.MapUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * @ClassName: CollectionUtils
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/11/22 15:11
 */
public class CollectionUtils {

    public CollectionUtils() {
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static String[] getValuesByKey(List<Map> mapList, String mapKey) {
        return getValuesByKey(mapList, mapKey, false);
    }

    public static String[] toStringArray(List mapList) {
        String[] result = new String[mapList.size()];

        for(int i = 0; i < mapList.size(); ++i) {
            Object o = mapList.get(i);
            result[i] = o != null ? o.toString() : null;
        }

        return result;
    }

    public static Long[] toLongArray(List mapList) {
        Long[] result = new Long[mapList.size()];

        for(int i = 0; i < mapList.size(); ++i) {
            Number answer = getNumber(mapList.get(i));
            if (answer == null) {
                result[i] = null;
            } else {
                result[i] = answer instanceof Long ? (Long)answer : answer.longValue();
            }
        }

        return result;
    }

    public static Integer[] toIntegerArray(List mapList) {
        Integer[] result = new Integer[mapList.size()];

        for(int i = 0; i < mapList.size(); ++i) {
            Number answer = getNumber(mapList.get(i));
            if (answer == null) {
                result[i] = null;
            } else {
                result[i] = answer instanceof Integer ? (Integer)answer : answer.intValue();
            }
        }

        return result;
    }

    private static Number getNumber(Object answer) {
        if (answer != null) {
            if (answer instanceof Number) {
                return (Number)answer;
            }

            if (answer instanceof String) {
                try {
                    String text = (String)answer;
                    return NumberFormat.getInstance().parse(text);
                } catch (ParseException var2) {
                }
            }
        }

        return null;
    }

    public static String[] getValuesByKey(List<Map> mapList, String mapKey, boolean autoFilterNullValue) {
        List<String> retList = new ArrayList();
        getValuesByKey0(mapList, mapKey, autoFilterNullValue, retList);
        return (String[])retList.toArray(new String[0]);
    }

    private static void getValuesByKey0(List<Map> mapList, String mapKey, boolean autoFilterNullValue, List<String> retList) {
        Iterator var4 = mapList.iterator();

        while(var4.hasNext()) {
            Map map = (Map)var4.next();
            String value = (String)map.get(mapKey);
            if (autoFilterNullValue) {
                if (StringUtil.isNotEmpty(value)) {
                    retList.add(value);
                }
            } else {
                retList.add(value);
            }
        }

    }

    public static List getValueListByKey(List<Map> mapList, String mapKey, boolean autoFilterNullValue) {
        List<String> retList = new ArrayList();
        getValuesByKey0(mapList, mapKey, autoFilterNullValue, retList);
        return retList;
    }

    public static Long[] getLongValuesByKey(List<Map> mapList, String mapKey) {
        return getLongValuesByKey(mapList, mapKey, false);
    }

    public static Long[] getLongValuesByKey(List<Map> mapList, String mapKey, boolean autoFilterNullValue) {
        List<Long> retList = new ArrayList();
        getLongValuesByKey0(mapList, mapKey, autoFilterNullValue, retList);
        return (Long[])retList.toArray(new Long[0]);
    }

    public static List getLongValueListByKey(List<Map> mapList, String mapKey, boolean autoFilterNullValue) {
        List<Long> retList = new ArrayList();
        getLongValuesByKey0(mapList, mapKey, autoFilterNullValue, retList);
        return retList;
    }

    private static void getLongValuesByKey0(List<Map> mapList, String mapKey, boolean autoFilterNullValue, List<Long> retList) {
        Iterator var4 = mapList.iterator();

        while(var4.hasNext()) {
            Map map = (Map)var4.next();
            Long value = MapUtils.getLong(map, mapKey);
            if (autoFilterNullValue) {
                if (value != null) {
                    retList.add(value);
                }
            } else {
                retList.add(value);
            }
        }

    }

    public static Integer[] getIntegerValuesByKey(List<Map> mapList, String mapKey) {
        return getIntegerValuesByKey(mapList, mapKey, false);
    }

    public static Integer[] getIntegerValuesByKey(List<Map> mapList, String mapKey, boolean autoFilterNullValue) {
        List<Integer> retList = new ArrayList();
        getIntegerValuesByKey0(mapList, mapKey, autoFilterNullValue, retList);
        return (Integer[])retList.toArray(new Integer[retList.size()]);
    }

    public static List getIntegerValueListByKey(List<Map> mapList, String mapKey, boolean autoFilterNullValue) {
        List<Long> retList = new ArrayList();
        getLongValuesByKey0(mapList, mapKey, autoFilterNullValue, retList);
        return retList;
    }

    private static void getIntegerValuesByKey0(List<Map> mapList, String mapKey, boolean autoFilterNullValue, List<Integer> retList) {
        Iterator var4 = mapList.iterator();

        while(var4.hasNext()) {
            Map map = (Map)var4.next();
            Integer value = MapUtils.getInteger(map, mapKey);
            if (autoFilterNullValue) {
                if (value != null) {
                    retList.add(value);
                }
            } else {
                retList.add(value);
            }
        }

    }


    public static Number sumListMap(List<Map> mapList, String key) {
        if (isEmpty((Collection)mapList)) {
            return 0;
        } else {
            Object object = ((Map)mapList.get(0)).get(key);
            if (object instanceof Integer) {
                int result = 0;

                Map map;
                for(Iterator var4 = mapList.iterator(); var4.hasNext(); result += MapUtils.getInteger(map, key, 0)) {
                    map = (Map)var4.next();
                }

                return result;
            } else if (!(object instanceof Double)) {
                return 0;
            } else {
                double result = 0.0D;

                Map map;
                for(Iterator var5 = mapList.iterator(); var5.hasNext(); result += MapUtils.getDouble(map, key, 0.0D)) {
                    map = (Map)var5.next();
                }

                return result;
            }
        }
    }

    public static Number sumGroupByMonth(List<Map> mapList, String sumKey, String dateKey) {
        if (isEmpty((Collection)mapList)) {
            return 0;
        } else {
            Object object = ((Map)mapList.get(0)).get(sumKey);
            if (object instanceof Integer) {
                int result = 0;

                Map map;
                for(Iterator var5 = mapList.iterator(); var5.hasNext(); result += MapUtils.getInteger(map, sumKey, 0)) {
                    map = (Map)var5.next();
                }

                return result;
            } else if (!(object instanceof Double)) {
                return 0;
            } else {
                double result = 0.0D;

                Map map;
                for(Iterator var6 = mapList.iterator(); var6.hasNext(); result += MapUtils.getDouble(map, sumKey, 0.0D)) {
                    map = (Map)var6.next();
                }

                return result;
            }
        }
    }
}
