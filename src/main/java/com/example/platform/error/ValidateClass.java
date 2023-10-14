package com.example.platform.error;

import com.example.platform.dto.JsonResult;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.EmployeeDTO;
import com.example.platform.pojo.UserDTO;
import com.example.platform.pojo.constants.ComFinalParams;
import com.example.platform.service.util.JsonUtil;
import com.example.platform.utils.StringUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.spel.ast.Operator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 作       者：zhaoxin
 * 最后修改时间：2018-3-21
 * 描      述：验证的抽象类，一些通用的验证和处理
 */
@Slf4j
public abstract class ValidateClass {

    private String logTag;

    protected void setLogTag(String logTag){
        this.logTag = logTag;
    }

    public <T extends Object> void ObjectNotNull(T t, T obj, BaseErrorCode baseErrorCode) throws BizException {
        if (t == null) {
            log.warn(logTag + " 对象 " + obj.getClass().getSimpleName().toLowerCase() + " not exist");
            throw new BizException(baseErrorCode);
        }
    }

    /**
     * 所有字符不能为空异常处理
     */
    public void paramNotNull(CharSequence... css) throws BizException {
        boolean anyEmpty = StringUtils.isAnyEmpty(css);
        if (anyEmpty) {
            log.warn(logTag + " not null params is null");
            throw new BizException(GlobalErrorCode.PARAM_NULL_ERROR.getCode(),
                    logTag + GlobalErrorCode.PARAM_NULL_ERROR.getMessage());
        }
    }

    /**
     * 所有对象不为空异常处理
     */
    public void objectNotNull(Object... objects) throws BizException {
        for (Object obj : objects) {
            if (obj == null) {
                log.warn(logTag + " not null params is null");
                throw new BizException(GlobalErrorCode.PARAM_NULL_ERROR.getCode(),
                        logTag + GlobalErrorCode.PARAM_NULL_ERROR.getMessage());
            }
        }
    }

    public<T> void listNotEmpty(List<T>... lists) throws BizException {
        for (List<T> list : lists) {
            if (list == null || list.isEmpty()) {
                log.warn(logTag + " not null params is null");
                throw new BizException(GlobalErrorCode.PARAM_NULL_ERROR);
            }
        }
    }

    public void OperatorNotNull(Operator operator) throws BizException {
        if (operator == null){
            log.warn(logTag + " operator is null");
            throw new BizException(GlobalErrorCode.USER_NOT_LOGIN);
        }
    }

    public void EmployeeNotNull(EmployeeDTO operator) throws BizException {
        if (operator == null){
            log.warn(logTag + " operator is null");
            throw new BizException(GlobalErrorCode.USER_NOT_LOGIN);
        }
    }

    public void UserNotNull(UserDTO user) throws BizException {
        if (user == null){
            log.warn(logTag + " user is null");
            throw new BizException(GlobalErrorCode.USER_NOT_LOGIN);
        }
    }
    /**
     * 加密异常处理
     */
    public String SHAException(String password) throws BizException {
        try {
            password = StringUtil.getSHA(password);
            return password;
        } catch (NoSuchAlgorithmException e) {
            log.error(logTag + " 未找到加密方法, \n {}", e);
            throw new BizException(GlobalErrorCode.UNKNOWN);
        } catch (UnsupportedEncodingException e) {
            log.error(logTag + " 格式转换错误, \n {}", e);
            throw new BizException(GlobalErrorCode.UNKNOWN);
        }
    }

    /**
     * List结果集处理
     * @param result
     *      json结果集
     * @param typeReference
     *      类型
     * @param serviceName
     *      调用的服务
     */
    public <T> T getJsonListData(JsonResult result, TypeReference<T> typeReference, String serviceName) throws BizException {
        this.getJsonDataDeal(result, serviceName);
        if (typeReference == null) {
            return null;
        }
        Object data = result.getData();
        T json = JsonUtil.stringToBean(JsonUtil.beanToJson(data), typeReference);
        log.info("connect " + serviceName + " has success:" + json);
        return json;
    }

    /**
     * 对象结果集处理
     * @param result
     *      json结果集
     * @param t
     *      类型
     * @param serviceName
     *      调用的服务
     */
    public <T> T getJsonData(JsonResult result, Class<T> t, String serviceName) throws BizException {
        this.getJsonDataDeal(result, serviceName);
        if (t == null) {
            return null;
        }
        Object data = result.getData();
        T json = JsonUtil.fromJson(JsonUtil.beanToJson(data), t);
        log.info("connect " + serviceName + " has success； result = " + json);
        return json;
    }

    //调用服务相应处理
    public void getJsonDataDeal(JsonResult result, String serviceName) throws BizException {
        if (!result.getCode().equals(ComFinalParams.MESSAGE_SUCCESS)) {
            log.error("connect " + serviceName + " has fail or has error； result = " + result);
            throw new BizException(result.getCode(), result.getMessage());
        }
    }
}
