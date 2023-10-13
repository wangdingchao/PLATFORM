package com.example.platform.pojo;

import com.example.platform.pojo.enums.DataPrivilege;
import com.example.platform.pojo.enums.ServiceType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-9-25
 * 描述：模块数据权限
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataPower {

    //职位id
    private String positionId;

    //模块类型
    private ServiceType serviceType;

    //数据权限
    private DataPrivilege dataPrivilege;

    //相关id集合（WE：员id集合；WEDEPART：部门的id集合）
    private Set<String> resultIds;

    //权限(本部门及下级部门以及指定的部门)和组织机构关系
    private List<PositionPrivilegeOrgRelation> privilegeOrgRelations;

    private List<String> extraOrgIds; //权限(本部门及下级部门以及指定的部门)和组织机构关系
}
