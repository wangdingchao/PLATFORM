package com.example.platform.controller.req;


import com.example.platform.error.GlobalErrorCode;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.EmployeeDTO;

/**
 * @author liujiaming
 */
public class ReqHolder {


    private static ThreadLocal<ReqInfo> reqInfoThreadLocal = new ThreadLocal();

    public static void setReqInfo(ReqInfo reqInfo) {
        reqInfoThreadLocal.set(reqInfo);
    }

    public static ReqInfo getReqInfo() {
        return reqInfoThreadLocal.get();
    }

    public static EmployeeDTO getLoginUser() throws BizException {
        ReqInfo reqInfo = getReqInfo();
        if(reqInfo != null && reqInfo.loginUser != null) {
            return reqInfo.loginUser;
        }
        throw new BizException(GlobalErrorCode.USER_NOT_LOGIN);
    }

    public static String getLoginUserCompanyCode() throws BizException {
        return getLoginUser().getCompanyCode();
    }

    public static String getLoginUserId() throws BizException {
        return getLoginUser().getId();
    }

    public static String getLoginUserMobile() throws BizException {
        return getLoginUser().getMobile();
    }

    public static String getLinkId() {
        ReqInfo reqInfo = getReqInfo();
        if(reqInfo != null) {
            return reqInfo.linkId;
        }
        return null;
    }

    public static void remove(){
        reqInfoThreadLocal.remove();
    }
}
