package com.example.platform.pojo;

import com.example.platform.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeOrganization {

    private String id;

    //员工id
    private String empId;

    //职位id
    private String positionId;

    @ApiModelProperty(hidden = true)
    private Position position;

    //组织机构id
    private String orgId;

    @ApiModelProperty(hidden = true)
    private Organization organization;

    //任职开始时间
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date effectiveTime;

    //状态 1:有效 0:无效
    @ApiModelProperty(hidden = true)
    private Boolean valid;

    //任职结束时间
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date invalidTime;

    //主职位
    private boolean primary;

    public EmployeeOrganization(String empId, String positionId, String orgId) {
        this.id = StringUtil.uuid();
        this.empId = empId;
        this.positionId = positionId;
        this.orgId = orgId;
        this.valid = true;
    }

}
