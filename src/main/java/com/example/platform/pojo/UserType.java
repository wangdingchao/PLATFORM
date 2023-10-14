package com.example.platform.pojo;

import com.example.platform.pojo.enums.BaseEnum;
import lombok.Getter;

/**
 * 用户的类型枚举类
 */
@Getter
public enum UserType implements BaseEnum {

    E("员工"),
    V("志愿者"),
    A("运动员");

    private String message;

    private UserType(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
