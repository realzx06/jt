package cn.jt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //1.对象转Json
    public static String toJson(Object obj) {

        try {
            String json = MAPPER.writeValueAsString(obj);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);//将检查异常转为运行异常
        }

    }

    //1.JSON转对象
    public static <T> T toObject(String json,Class<T> targetClass) {

        try {
            T t= MAPPER.readValue(json, targetClass);
            return  t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);//将检查异常转为运行异常
        }

    }
}