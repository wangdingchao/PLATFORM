package com.example.platform.pojo;

import lombok.Data;

/**
 * 经销商相关模型基类，区分数据所属经销商
 */
@Data
public class DealerModel extends BaseModel {

    //经销商5位编码
    protected String dealerCode;
}
