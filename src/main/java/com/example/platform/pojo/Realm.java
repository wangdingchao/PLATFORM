package com.example.platform.pojo;


import com.example.platform.pojo.enums.BaseEnum;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Realm implements BaseEnum {
    //用户标签
    USER("用户", true),

    //客户标签
    CUSTOMER("客户", true),

    //员工标签
    EMPLOYEE("员工"),

    //销售机会标签
    OPPORTUNITY("销售机会", true),

    //线索
    CLUES("线索", true),

    //订单标签
    ORDER("订单", true),

    //职位标签
    POSITION("职位", true),

    //组织机构标签
    ORGANIZATION("组织机构", true),

    //任务标签
    TASK("任务", true),

    // 工作流标签
    WORKFLOW("工作流", true),

    //工单标签
    SERVICE_ORDER("工单", true),

    SERVICE_SUB_ORDER("工单", true),

    REMARK("备注"),

    RECORD("记录"),

    REFUND("退款"),

    SAMPLE("上样单", true),

    BUDGET("预算单", true),

    SUPPLEMENT("增补单", true),

    BRAND("品牌"),

    PAYMENT("收款"),

    INVOICE("发票"),

    CHANGE_ORDER("退换货"),

    BRAND_ORDER("品牌单"),

    CUST_ORDER("客户订单"),

    SUB_ORDER("品牌子单"),

    PRODUCT("产品"),

    RELATION("产品关系表"),

    COUPON("优惠券"),

    COOPERATOR("合作方"),

    LINBAO("拎包"),

    PLAN("计划"),

    DELIVERY("物流"),

    INSTALL("安装"),

    EVALUATE("评价"),

    INSTALL_PACKAGE_EXCEPTION("安装包数异常"),

    INSTALL_ACCOUNT("安装特殊工费"),

    AFTER_SALES("售后"),

    REPOSITORY("仓库"),

    OLD_CUSTOMER("老客户"),

    FEE("服务费"),
    SOGAL_CONTRACT("索菲亚合同"),
    SCHMIDT_CONTRACT("司米合同"),
    MILANA_CONTRACT("米兰纳合同"),

    ACTIVITY("营销活动"),

    OCR("文件识别"),

    CONTRACT("合同附件"),
    CONTRACT_SHOP("承包合同附件"),

    SPACE_LAYOUT("空间布局图"),

    EVALUATE_IMAGE("评价图片"),
    BUDGET_OA_AUDIT("预算签批"),

    MEASURE("测量完成"),

    DELIVERY_P("送货交单"),

    TEAM("纵队"),
    TEAM_EMP("纵队人员"),

    SIGN_IN_APPEAL("签到申诉"),

    CATEGORY("品类"),

    PURCHASE_ORDER("购货单");

    private String message;

    /**
     * 是否为标签分组
     */
    private boolean tag = false;

    Realm(String message) {
        this.message = message;
    }

    Realm(String message, boolean tag) {
        this(message);
        this.tag = tag;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    public boolean isTag() {
        return this.tag;
    }

    public static Realm findByMessage(String message){
        return Arrays.stream(Realm.values()).parallel().filter(realm -> realm.getMessage().equals(message)).findAny().orElse(null);
    }
}
