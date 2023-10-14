package com.example.platform.pojo.enums;

import lombok.Getter;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-10-25
 * 描述：需要区分终端类型的操作的枚举类
 */
@Getter
public enum Realm implements BaseEnum {

    PAYMENT("收款"),
    REFUND("退款"),
    ADD_CUSTOMER("集客"),
    ADD_HOUSE("追加房屋"),
    ADD_OPPORTUNITY("追加机会"),
    CREATE_TASK("创建任务"),
    FINISH_TASK("完成任务"),
    LOGIN("登陆"),
    WORKFLOW_ACTION("操作工作流程"),
    WORKFLOW_CREATE_PROCESS("创建工作流成"),
    VIEW_CUST_DETAIL("查看客户详情"),
    INSTALL_LOGIN("安装小程序登陆"),
    DELIVERY_LOGIN("物流小程序登陆"),
    INSTALL_ARRANGE_ORDER("安装派单"),
    INSTALL_SUBMIT_ORDER("安装交单");

    private String message;

    private Realm(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }

}
