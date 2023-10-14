package com.example.platform.mapper;

import com.example.platform.pojo.DataPower;
import com.example.platform.pojo.enums.MenuScope;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-1-24
 * 描述：权限相关的dao
 */
@Mapper
public interface AuthMapper {

    void deleteByRole(@Param("roleId") String roleId);

    void deleteByPrivilege(@Param("privilege") String privilege, @Param("companyCode") String companyCode);

    void deleteDataPower(@Param("positionId") String positionId);

    void addRolePrivileges(@Param("roleId") String roleId, @Param("privileges") Set<String> privileges);

    void addPrivilegeRoles(@Param("privilege") String privilege, @Param("roles") Set<String> roles);

    void addDataPower(@Param("dataPowers") List<DataPower> dataPowers);

    List<String> findRolePrivilege(@Param("roleIds") String [] roleIds);

    List<String> findByRole(@Param("roleId") String roleId);

    List<String> findByUser(@Param("userId") String userId, @Param("scope") MenuScope scope);

//    CallCenterAgent findByAgent(@Param("userId") String userId);

    List<DataPower> findDataPowerByPositionId(@Param("positionId") String positionId);
}
