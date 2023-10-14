package com.example.platform.pojo;

import com.example.platform.pojo.enums.DataPrivilege;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Position implements Serializable {

    private String companyCode;

    private String id;

    //名称
    @Length(max = 100)
    private String name;

    //职位编码
    private String seq;

    //别名
    @Length(max = 100)
    private String alias;

    //所属组织机构id
    private String orgId;

    //职位具有的角色id
    private String roleId;

    //是否删除
    private Boolean valid;

    //数据权限
    private DataPrivilege dataPrivilege;

    //dsp角色id
    private List<String> roleIds;

    //xPlan角色id
    private List<String> xRoleIds;

    @Transient
    private List<DictPrivilege> dictPrivileges;     //职位的业务权限

    @Transient
    private Organization organization;               //所属组织机构实体类

    @Transient
    private List<Tag> tags;                          //职位具有的标签

    @Transient
    private List<DataPower> dataPowers;             //职位具有的模块权限

    @Transient
    private List<String> extraOrgIds;

    private String positionId;

    private Map<String,List<String>> bizPrivileges;

    public Position(String name) {
        this.name = name;
    }

}
