package com.example.platform.pojo.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-2-21
 * 描述：数据权限的枚举类
 */
@Getter
public enum DataPrivilege implements BaseEnum{

    ALL("全部",1),
    I("本人",2),
    WE("本人及下属",3),
    DEPARTMENT("本部门",4),
    WEDEPART("本部门及下级部门",5),
    WEANDASSIGNDEPART("跨部门",6);

    private String message;

    private Integer sort;

    private DataPrivilege(String message){ this.message = message;}

    @Override
    public String getCode(){return this.name();}

    public Integer getSort() {
        return this.sort;
    }

    DataPrivilege(String message, int sort){
        this.message = message;
        this.sort = sort;
    }


    public static List<DataPrivilege> getDataPrivilegeList() {
        return  Arrays.stream(DataPrivilege.values()).sorted(Comparator.comparing(DataPrivilege::getSort)).collect(Collectors.toList());
    }
}
