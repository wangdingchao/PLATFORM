package com.example.platform.pojo.enums;

import lombok.Getter;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-2-21
 * 描述：数据权限的枚举类
 */
@Getter
public enum ServiceType implements BaseEnum {

    CUSTOMER("客户模块"),
    ORDER("订单模块"),
    FINANCE("财务模块"),
    CHANNEL("渠道模块"),
    INSTALL("安装模块"),
    DELIVERY("物流模块"),
    AFTERSALES("售后模块"),
    SJLB("社交裂变模块"),
    BUDGET("预算模块"),
    CALL_CENTER("呼叫系统模块"),
    PLAN("计划模块"),
    REAWRD_LIST("奖励列表模板"),
    REAWRD_EXTRACT("奖励提取模块"),
    EARNEST_MONEY("保证金列表"),
    EARNEST_MONEY_RETURN("保证金返还"),
    TEAM_ORG("纵队部门列表"),
    EMPLOYEE_FINE_ACCOUNT("员工扣罚账户"),
    RECRUITERS("入职管理模块"),
    SALARY_FILE("薪资档案"),
    IDS("IDS服务"),
    ;

    private String message;

    private ServiceType(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();

    }

}
