package com.example.platform.pojo;

import com.example.platform.pojo.enums.BaseEnum;
import lombok.Getter;

/**
 * 用户的类型枚举类
 */
@Getter
public enum UserType implements BaseEnum {

    C("客户"),
    E("员工"),
    H("合作方"),
    U("普通用户");

    private String message;

    private UserType(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
