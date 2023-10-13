package com.example.platform.dto;

import com.example.platform.error.GlobalErrorCode;
import lombok.Data;

/**
 * @author wdc
 * @date 2023/10/10
 * @description 返回结果通用类
 */

@Data
public class JsonResult<T> {

    public static String SUCCESS_CODE = "200";

    public static String FAILURE_CODE = "500";

    public String message;

    public String code;

    public String status;

    public T data;

    public JsonResult(String code) {
        this.code = code;
    }

    public JsonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功编码，当没有返回值时，可以调用这个方法
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success() {
        return (JsonResult<T>) success((Object)null);
    }

    /**
     * 返回成功编码 ，数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(SUCCESS_CODE, (String)null, data);
    }

    /**
     * 返回结果，以及错误信息
     * @param data 结果
     * @param error 错误信息
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> fail(T data,String error) {
        return new JsonResult(FAILURE_CODE,error,data);
    }

    /**
     * 是否是常见error，不是的话返回错误信息
     * @param ex exception
     * @return
     */
    public static JsonResult fail(Throwable ex) {
        return new JsonResult(GlobalErrorCode.UNKNOWN.getCode(), ex == null ? null : ex.getMessage(), (Object)null);
    }

    /**
     * 返回错误编码
     * @param code
     * @return
     */
    public static JsonResult fail(String code) {
        return new JsonResult(code, (String)null, (Object)null);
    }

    /**
     * 返回错误编码已经错误信息
     * @param code
     * @param message
     * @return
     */
    public static JsonResult fail(String code, String message) {
        return new JsonResult(code, message, (Object)null);
    }

    /**
     * 返回错误编码，数据
     * @param code
     * @param data
     * @return
     */
    public static JsonResult fail(String code, Object data) {
        return new JsonResult(code, (String)null, data);
    }

    /**
     * 返回错误编码，错误信息 ，数据
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static JsonResult fail(String code, String message, Object data) {
        return new JsonResult(code, message, data);
    }





}
