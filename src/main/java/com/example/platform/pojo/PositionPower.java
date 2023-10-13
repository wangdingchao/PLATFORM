package com.example.platform.pojo;

import com.example.platform.pojo.enums.DataPrivilege;
import com.example.platform.pojo.enums.ServiceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.*;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-3-2
 * 描述：职位授权DTO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PositionPower {

    private String id;                              //职位id

    private DataPrivilege dataPrivilege;           //数据权限

    private DataPrivilege areaPrivilege;           //区域权限

    @ApiModelProperty(hidden = true)
    private Set<String> resultIds;                 //相关id集合（WE：员id集合；WEDEPART：部门的id集合）

    private List<DictPrivilege> dictPrivileges;     //业务权限

    private List<DataPower> dataPowers;             //模块的数据权限

    private List<String> roleIds;                  //角色id集合

    private List<String> xRoleIds;                //x计划角色id

    private List<PositionPrivilegeOrgRelation> privilegeOrgRelations; //权限(本部门及下级部门以及指定的部门)和组织机构关系

    private List<String> extraOrgIds; //权限(本部门及下级部门以及指定的部门)和组织机构关系

    @JsonIgnore
    public Map<String, List<DictPrivilege>> getLabelDict() {
        if ((this.dictPrivileges == null) || this.dictPrivileges.isEmpty()) {
            return null;
        }
        Map<String, List<DictPrivilege>> map = new HashMap<>();
        for (DictPrivilege dict : this.dictPrivileges) {
            if (!map.containsKey(dict.getDictType().getCode())) {
                map.put(dict.getDictType().getCode(), new ArrayList<>());
            }
            List<DictPrivilege> list = map.get(dict.getDictType().getCode());
            list.add(dict);
            map.put(dict.getDictType().getCode(), list);
        }
        return map;
    }

    @JsonIgnore
    public Map<String, List<String>> getBizPrivileges() {
        Map<String, List<String>> map = new HashMap<>();
        if ((this.dictPrivileges == null) || this.dictPrivileges.isEmpty()) {
            return map;
        }
        for (DictPrivilege dict : this.dictPrivileges) {
            if (!map.containsKey(dict.getDictType().getCode())) {
                map.put(dict.getDictType().getCode(), new ArrayList<>());
            }
            List<String> list = map.get(dict.getDictType().getCode());
            list.add(dict.getDictValue());
            map.put(dict.getDictType().getCode(), list);
        }
        return map;
    }

    @JsonIgnore
    public DataPower getDataPower(ServiceType serviceType) {
        if ((this.dataPowers != null) && !this.dataPowers.isEmpty() && (serviceType != null)) {
            for (DataPower dataPower : this.dataPowers) {
                if (serviceType.equals(dataPower.getServiceType())) {
                    return dataPower;
                }
            }
        }
        return null;
    }
}
