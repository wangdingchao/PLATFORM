package com.example.platform.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-1-25
 * 描述：用户表的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends User{

    private String name;                    //用户的名字

    private String companyCode;           // 公司编码

    private Long newPrivilegeTime;       //最新的权限更新时间

    private List<String> privileges;        //用户具有功能的权限

    private List<Position> positions;       //用户的职位

    private EmployeeDTO employee;           //员工信息



    /**
     * 主要职位
     */
    private Position primaryPosition;

    /**
     * 切换用户
     */
    private UserDTO mockBy;

    /**
     * 过期时间
     */
    private Integer ttl;

    /**
     * 角色
     */
    private List<Role> role;

    /**
     * IDS用户对象
     */
    private LoginUser idsUser;

    private Boolean isleader = false;

    private String leaderId;

    private String providerId;

    private String openId;

    protected String wechatOpenId;

    /**
     * 可切换公司编码
     */
    private List<String> switchableCompanyCodes;

    private Map<String, List<String>> menuPrivilege;

    public UserDTO(String id, String mobile) {
        this.id = id;
        this.mobile = mobile;
    }

    public String jwt;

    private List<DataPower> dataPowers;

    /**
     * ids 登录 token
     */
    private String iDSAccessToken;

    /**
     * 用户拥有得经销商权限
     */
    private Set<String> dealerCodes;

    private PositionPower positionPower;

    /**
     * 页面内多公司权限
     */
    protected LinkedHashSet<String> innerPageCompanyCodes;

    /**
     * 页面内经销商编码
     */
    protected LinkedHashSet<String> innerPageDealerCodes;


}
