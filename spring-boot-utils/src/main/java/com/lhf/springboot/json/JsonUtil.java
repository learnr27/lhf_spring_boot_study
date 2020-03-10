package com.lhf.springboot.json;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lhf.springboot.exception.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: JsonUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 15:42
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectMapper xmlMapper = new XmlMapper();

    public JsonUtil() {
    }

    public static void initMapper(ObjectMapper mapper) {
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(fmt);
        mapper.getSerializerProvider().setNullKeySerializer(new JsonSerializer() {
            public void serialize(Object key, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                jgen.writeFieldName("");
            }
        });
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.SETTER, Visibility.NONE);
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        } else {
            try {
                StringWriter writer = new StringWriter();
                mapper.writeValue(writer, obj);
                return writer.toString();
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    public static String toJson(Object[] obs) {
        if (obs == null) {
            return "";
        } else {
            try {
                String string = mapper.writeValueAsString(obs);
                return string;
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    public static List<Map> getListByJson(String json) {
        if (StringUtils.isBlank(json)) {
            return new ArrayList();
        } else {
            try {
                List<Map> list = (List)mapper.readValue(json, List.class);
                return list;
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    public static <T> List<T> getListByJson(String json, Class<T> c) {
        if (StringUtils.isBlank(json)) {
            return new ArrayList();
        } else {
            try {
                List<T> list = (List)mapper.readValue(json, getCollectionType(ArrayList.class, c));
                return list;
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T getObjectByJson(String json, Class<T> c) {
        if (StringUtils.isBlank(json)) {
            return null;
        } else {
            try {
                T t = mapper.readValue(json, c);
                return t;
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    public static Map getMapByJson(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return new HashMap();
        } else {
            try {
                HashMap m = (HashMap)mapper.readValue(jsonStr, HashMap.class);
                return m;
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    public static String toXml(Object obj) {
        if (obj == null) {
            return "";
        } else {
            try {
                StringWriter writer = new StringWriter();
                xmlMapper.writeValue(writer, obj);
                return writer.toString();
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    public static List<Map> getListByXml(String xml) {
        if (StringUtils.isBlank(xml)) {
            return new ArrayList();
        } else {
            try {
                List<Map> list = (List)xmlMapper.readValue(xml, List.class);
                return list;
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    public static <T> List<T> getListByXml(String xml, Class<T> c) {
        if (StringUtils.isBlank(xml)) {
            return new ArrayList();
        } else {
            try {
                List<T> list = (List)xmlMapper.readValue(xml, getCollectionType(ArrayList.class, c));
                return list;
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    public static <T> T getObjectByXml(String xmlStr, Class<T> c) {
        if (StringUtils.isBlank(xmlStr)) {
            return null;
        } else {
            try {
                T t = xmlMapper.readValue(xmlStr, c);
                return t;
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    public static Map getMapByXml(String xmlStr) {
        if (StringUtils.isBlank(xmlStr)) {
            return new HashMap();
        } else {
            try {
                HashMap m = (HashMap)xmlMapper.readValue(xmlStr, HashMap.class);
                return m;
            } catch (Exception e) {
                logger.error("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
                throw new ExceptionUtil("sys.jsonUtil.error", new String[]{e.getMessage()}, e);
            }
        }
    }

    static {
        initMapper(mapper);
        initMapper(xmlMapper);
    }
}

