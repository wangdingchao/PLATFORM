package com.example.platform.pojo.enums;

import com.example.platform.utils.ListUtil;
import lombok.Getter;

import java.util.Arrays;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-10-25
 * 描述：终端类型的枚举类
 */
@Getter
public enum TerminalType implements BaseEnum {

    WX("微信公众号"),
    MINAPP("微信小程序"),
    ANDROID("安卓"),
    IOS("苹果"),
    WEB("PC端"),
    DING("钉钉端"),
    UNION("未知端");

    private String message;

    private TerminalType(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }

    public static TerminalType TerminalTypeByCode(String code) {
        TerminalType[] values = TerminalType.values();
        if(null != values && values.length >0){
            return ListUtil.find(Arrays.asList(values), (i) -> i.getCode().equals(code));
        }
        return null;
    }

}
