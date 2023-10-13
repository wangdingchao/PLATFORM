package com.example.platform.pojo;

import com.example.platform.utils.ListUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 经销商的员工
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO extends Employee {

    protected List<EmployeeOrganization> employeeOrganizations;

//    //X计划名字
//    @JsonProperty("xName")
//    protected String xName;
//
//    //X计划password
//    @JsonProperty("xPassword")
//    protected String xPassword;
//
//    //X计划id
//    protected String xId;
    //X计划id
    protected String xid;

    protected String wechatOpenId;

    //职位（不是数据库字段）
    protected String positionId;

    //组织机构标识orgId（不是数据库字段）
    protected String orgId;

    protected List<String> areaCodes;
    protected List<String> dealerCodes;
    protected String companyCode;

    /**
     * 页面内多公司权限
     */
    protected Set<String> innerPageCompanyCodes;

    /**
     * 页面内经销商编码
     */
    protected Set<String> innerPageDealerCodes;

    //服务纵队id（不是数据库字段）
    @ApiModelProperty(hidden = true)
    protected String teamId;

    //服务纵队id（不是数据库字段）
    @ApiModelProperty(hidden = true)
    protected String connectorTeamId;

    @ApiModelProperty(hidden = true)
    protected Organization organization;

    @ApiModelProperty(hidden = true)
    protected Position position;

    @ApiModelProperty(hidden = true)
    protected List<Position> positions;

    @ApiModelProperty(hidden = true)
    protected List<MiddleDealerEmployee> middleDealerEmployees;

    private User user;
    //员工可以做的品牌
    @ApiModelProperty(hidden = true)
    protected Map<String, Boolean> brands;

    //身份证号，用于初始化密码
    private String idCard;

    //保证金
    private BigDecimal earnestMoney;
    //保证金缴费标准
    private BigDecimal earnestMoneyStandard;

    public BigDecimal getEarnestMoney() {
        if (null == this.earnestMoney) {
            return BigDecimal.ZERO;
        }
        return this.earnestMoney;
    }

    public String getPositionId() {
        if (!StringUtils.isBlank(this.positionId)) {
            return this.positionId;
        }
        if (employeeOrganizations == null) {
            return null;
        }
        EmployeeOrganization eo = ListUtil.find(employeeOrganizations, EmployeeOrganization::isPrimary);
        return eo == null ? null : eo.getPositionId();
    }

    public String getOrgId() {
        if (!StringUtils.isBlank(this.orgId)) {
            return this.orgId;
        }
        if (employeeOrganizations == null) {
            return null;
        }
        EmployeeOrganization eo = ListUtil.find(employeeOrganizations, EmployeeOrganization::isPrimary);
        return eo == null ? null : eo.getOrgId();
    }

}
