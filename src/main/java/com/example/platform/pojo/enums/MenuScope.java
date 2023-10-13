package com.example.platform.pojo.enums;

import lombok.Getter;

/**
 * 菜单作用域
 */
@Getter
public enum MenuScope implements BaseEnum {

    BI("报表"),
    DSP("主营"),
    IDS("工程"),
    CONSOLE("管理"),
    INSTALL("安装"),
    DELIVERY("物流"),
    CRM("索小米"),
    WECOM("企微"),
    MINIOA("微协同"),
    COOPERATION("合作方带单"),
    CFHL("厨房护理服务商");

    private String message;

    private MenuScope(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.name();
    }


    public static MenuScope getEnum(String value) {
        for (MenuScope e : MenuScope.values()) {
            if (e.toString().equals(value)) {
                return e;
            }
        }
        return null;
    }
}
