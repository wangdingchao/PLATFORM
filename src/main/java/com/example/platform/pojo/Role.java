package com.example.platform.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-2-21
 * 描述：角色表的实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {

    @Length(max = 36)
    private String id;                          //角色的id

    @Length(max = 100)
    private String name;                        //角色的名字

    @Length(max = 100)
    private String roleKey;                        //角色的名字

    @Length(max = 200)
    private String desc;                        //角色的描述

    @NotNull
    private Boolean isAlive;                    //是否启用

    @Length(max = 36)
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String creatorId;                       //创建角色用户的id

    @Length(max = 36)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String lastUpdatedBy;                    //修改角色信息用户的id'

    private List<String> privileges;                 //角色的功能权限

    private List<EmployeeDTO> employees;            //该角色包含的员工

    private Integer employeeNum;                    //角色下面的员工数量

    private String companyCode;


}
