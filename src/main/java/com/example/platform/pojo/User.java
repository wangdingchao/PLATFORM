package com.example.platform.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-1-25
 * 描述：用户表的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends DealerModel{

    protected String id;                      //用户id

    protected String mobile;                  //用户电话及登录账户

    protected String imageUrl;                //访问头像的uel

    protected String wechatOpenId;            //微信的openId

    protected String unionId;                  //微信unionId

    @JsonIgnore
    protected String password;                //登录密码

    protected Date lastLoginTime;             //最后登录时间

    protected UserType type;                    //用户的类型，C=客户，E=员工，H=合作方

    protected Boolean kujiale;                  //是否已经开通酷家乐账号
    private   Boolean kujialeVip;

    protected Boolean valid;                   //状态  1启用  0 禁用

    protected String secondType;//FSS:分销商

    protected Date lastChangePasswordTime;
}