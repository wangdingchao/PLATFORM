package com.example.platform.pojo;

import com.example.platform.pojo.enums.DataPrivilege;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者：zj
 * 最后修改时间：2023-5-18
 * 描述：职位数据权限-组织机构关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PositionPrivilegeOrgRelation {

    //职位id
    private String positionId;

    //模块类型
    private String serviceType;

    //数据权限
    private DataPrivilege dataPrivilege;

    //组织机构id
    private String  orgId;

}
