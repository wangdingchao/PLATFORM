
package com.example.platform.error;

import lombok.Getter;

/**
 * @author 17575
 */
@Getter
public enum OAuthErrorCode implements BaseErrorCode{

    THE_ACCOUNT_CANNOT_BE_EMPTY("2001", "账户不能为空"),
    THE_PASSWORD_CANNOT_BE_EMPTY("2002", "密码不能为空"),
    LOGIN_EXCEPTION_UNKNOWN_MODE("2003","登录异常,未找到该参数登录方式"),
    FIND_LOGIN_USER_ERROR("2004","获取登录用户失败"),
    HAVE_NO_PERMISSION("2005","没有使用权限"),
    TOKEN_MISSING("2006","token缺失"),
    BINDING_ERROR("2007","公众号用户手机号验证码绑定登录异常"),
    OPEN_ID_MISSING("2008","openId缺失"),
    SWITCH_COMPANY_FAIL("2009","切换公司异常"),
    ;

    private String code;
    private String message;

    private OAuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
