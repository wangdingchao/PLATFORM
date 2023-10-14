package com.example.platform.service.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JsonUtil {

    public static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    public static final ObjectMapper objectMapper = new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


    static {
        /**
         * 该特性决定parser将是否允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）。
         * 由于JSON标准说明书上面没有提到注释是否是合法的组成，所以这是一个非标准的特性；
         * 尽管如此，这个特性还是被广泛地使用。
         *
         * 注意：该属性默认是false，因此必须显式允许，即通过JsonParser.Feature.ALLOW_COMMENTS 配置为true。
         *
         */
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        /**
         * 这个特性决定parser是否将允许使用非双引号属性名字， （这种形式在Javascript中被允许，但是JSON标准说明书中没有）。
         *
         * 注意：由于JSON标准上需要为属性名称使用双引号，所以这也是一个非标准特性，默认是false的。
         * 同样，需要设置JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES为true，打开该特性。
         *
         */
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        /**
         * 该特性决定parser是否允许单引号来包住属性名称和字符串值。
         *
         * 注意：默认下，该属性也是关闭的。需要设置JsonParser.Feature.ALLOW_SINGLE_QUOTES为true
         *
         */
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        /**
         * 该特性决定parser是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）。
         * 如果该属性关闭，则如果遇到这些字符，则会抛出异常。
         * JSON标准说明书要求所有控制符必须使用引号，因此这是一个非标准的特性。
         *
         * 注意：默认时候，该属性关闭的。需要设置：JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS为true。
         *
         */
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        /**
         * 该特性可以允许接受所有引号引起来的字符，使用‘反斜杠\’机制：如果不允许，只有JSON标准说明书中 列出来的字符可以被避开约束。
         *
         * 由于JSON标准说明中要求为所有控制字符使用引号，这是一个非标准的特性，所以默认是关闭的。
         *
         * 注意：一般在设置ALLOW_SINGLE_QUOTES属性时，也设置了ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER属性，
         * 所以，有时候，你会看到不设置ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER为true，但是依然可以正常运行。
         *
         */
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);

        //忽略没有的字段 not marked as ignorable
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }


    public static String beanToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            logger.error("beanToJson(Object) 异常1:", e);
        } catch (JsonMappingException e) {
            logger.error("beanToJson(Object) 异常2:", e);
        } catch (IOException e) {
            logger.error("beanToJson(Object) 异常3:", e);
        }
        return null;
    }

    public static String toJsonString(Object object) {
        return beanToJson(object);
    }

    public static String beanToJson1(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param typeReference
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String json, TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
            //传入的json格式不符合
            logger.error("stringToBean(String, JsonTypeReference<T>) 异常1:", e);
        } catch (JsonMappingException e) {
            //转换的bean对象，没有对应的setter
            logger.error("没有对应的set方法", e);
        } catch (IOException e) {
            logger.error("stringToBean(String, JsonTypeReference<T>) 异常3:", e);
        }
        return null;
    }

    public static JsonNode readTree(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            logger.error("readTree error:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 将json反序列化为对象
     *
     * @param json
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Class<T> type) {
        if (json == null) {
            return null;
        }
        try {
            return (T) objectMapper.readValue(json, type);
        } catch (JsonParseException e) {
            //传入的json格式不符合
            logger.error("stringToBean(String, JsonTypeReference<T>) 异常1:", e);
        } catch (JsonMappingException e) {
            //转换的bean对象，没有对应的setter
            logger.error("没有对应的set方法", e);
        } catch (IOException e) {
            logger.error("stringToBean(String, JsonTypeReference<T>) 异常3:", e);
        }
        return null;
    }

    public  static void  updateEntityValueByDTO(Object target,Object soure){

        ObjectReader objectReader = objectMapper.readerForUpdating(target);
        try {
            objectReader.readValue(beanToJson(soure));
        } catch (IOException e) {
            logger.error("beanToJson(Object) 异常:", e);
        }
    };


}
