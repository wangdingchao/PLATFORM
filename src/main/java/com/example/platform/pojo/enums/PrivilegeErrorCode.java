package com.example.platform.pojo.enums;

import com.example.platform.error.BaseErrorCode;
import lombok.Getter;

@Getter
public enum PrivilegeErrorCode implements BaseErrorCode {

    ROLE_ERROR_CODE("1901", "角色不存在"),
    ROLE_NAME_ISHAVE("1902", "角色名称已经存在"),
    USER_ERROR_CODE("1903", "用户名或密码错误"),
    TWO_PASSWORD_DIFFERENCE("1904", "两次密码不一致"),
    PASSWORD_IS_NULL("1905", "密码不能为空"),
    USER_TYPE_IS_NULL("1906", "用户类型不存在"),
    USER_IS_BAN("1907", "用户被禁用，请联系管理员"),
    EMP_NO_POSITION("1908", "员工没有职位"),
    PASSWORD_IS_ERROR("1909", "用户密码错误"),
    USER_NOT_FIND("1910", "用户不存在"),


    MENU_NOT_FOUND("1911", "没有这个菜单"),
    MENU_NAME_DUPLICATE("1912","菜单名称重复"),
    MENU_EXIST_CHILDREN("1913","存在下级菜单,不允许删除！"),
    BUTTON_NOT_ALLOWED("1914","当前菜单层级不允许添加按钮！"),
    OPEN_ID_EXIST("1915", "一个微信号只能绑定一个用户"),
    SYS_VERSION_NOT_FIND("1916", "系统版本信息不存在"),
    VERSION_PUBLISH_FINISH("1917", "系统版本已经发布，不能修改"),
    MENU_SORT_ORDER("1918", "菜单排序字段不允许为空"),;

    private String code;
    private String message;

    private PrivilegeErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
