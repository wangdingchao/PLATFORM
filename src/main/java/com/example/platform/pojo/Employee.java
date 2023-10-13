package com.example.platform.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 经销商的员工
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee implements Serializable {

    //protected String dealerCode;

    protected String companyCode;

    //唯一标识id
    protected String id;

    //姓名
    protected String name;

    //手机号
    protected String mobile;

    //员工状态
    protected EmpStatus status;

    private String organizationId;

    private String positionId;


    public Employee(String id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public Employee(String id, String orgId, String positionId, String companyCode) {
        this.id = id;
        this.organizationId = orgId;
        this.positionId = positionId;
        this.companyCode = companyCode;
    }

    public Employee(String id, String orgId, String positionId, String companyCode, EmpStatus status) {
        this.id = id;
        this.organizationId = orgId;
        this.positionId = positionId;
        this.companyCode = companyCode;
        this.status = status;
    }
}
