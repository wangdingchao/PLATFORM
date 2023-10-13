package com.example.platform.pojo;

import com.example.platform.pojo.enums.DictPrivilegeType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-2-21
 * 描述：业务权限实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DictPrivilege {

    @Length(max = 36)
    private String id;                   //id

    @Length(max = 36)
    private String positionId;          //角色id

    @Length(max = 50)
    private DictPrivilegeType dictType;           //业务类型

    @Length(max = 100)
    private String dictValue;         //业务权限值

    private String dictLabel;         //业务权限名
}
