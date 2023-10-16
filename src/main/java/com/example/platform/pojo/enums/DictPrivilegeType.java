package com.example.platform.pojo.enums;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务权限类型
 */
@Getter
public enum DictPrivilegeType implements BaseEnum {

    DEALER_DATA("区域经销商", DictType.DEALER),
    KHLY_OPERATION("客户来源-录入", DictType.KHLY),
    KHLY_VIEW("客户来源-查看", DictType.KHLY),
    REGION("接单范围", DictType.REGION),//区域改为接单范围
    BRAND("品类", DictType.BRAND),//李伟
    SALES("营销活动", DictType.SALES), //云寿
    DICT_TYPE("业务类型", DictType.DICT_TYPE),
    COOPERATOR_TYPE_VIEW("合作方类型-查看", DictType.COOPERATOR_TYPE),
    COOPERATOR_TYPE_OPERATION("合作方类型-操作", DictType.COOPERATOR_TYPE),
    ORDER_TYPE("订单类型", DictType.ORDER_TYPE),
    CMS_DEALER_ENUMERATE("经销商", DictType.CMS_DEALER_ENUMERATE),
    CMS_COLUMN("cms栏目", DictType.CMS_COLUMN),
    HHR_SOURCE("合伙人所属渠道", DictType.KHLY),

//  -------  START ADD ------------
    MINI_APP_MANAGE("小程序管理", DictType.MINI_APP),
    BIG_SCREEN_MANAGE("大屏管理",DictType.BIG_SCREEN),
    INFORMATION_MANAGE("")
    ;

    private String message;

    private DictType type;


    DictPrivilegeType(String message) {
        this.message = message;
    }

    DictPrivilegeType(String message, DictType type) {
        this(message);
        this.type = type;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    public static List<DictType> getDictTypes() {
        DictPrivilegeType[] dictPrivilegeTypes = DictPrivilegeType.values();
        List<DictType> list = new ArrayList<>();
        for (DictPrivilegeType dictPrivilegeType : dictPrivilegeTypes) {
            list.add(dictPrivilegeType.getType());
        }
        return list;
    }
}