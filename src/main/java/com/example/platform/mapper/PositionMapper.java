
package com.example.platform.mapper;

import com.example.platform.pojo.DictPrivilege;
import com.example.platform.pojo.Position;
import com.example.platform.pojo.enums.DataPrivilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 作       者：zhaoxin
 * 最后修改时间：2018-3-22
 * 描      述：职位管理mapper
 */
@Mapper
public interface PositionMapper {

    int countByCondition(@Param("name") String name, @Param("alias") String alias,
                         @Param("orgIds") Set<String> orgIds, @Param("valid") Boolean valid,
                         @Param("label") List<String> label, @Param("dealerCodes") List<String> dealerCodes);

    List<Position> findPageByCondition(@Param("name") String name, @Param("alias") String alias,
                                       @Param("orgIds") Set<String> orgIds, @Param("valid") Boolean valid,
                                       @Param("label") List<String> label, @Param("dealerCodes") List<String> dealerCodes,
                                       @Param("offset") int offset, @Param("size") int size);

    List<Position> findListByPosition(@Param("name") String name, @Param("alias") String alias,
                                      @Param("orgId") String orgId, @Param("valid") Boolean valid,
                                      @Param("dealerCodes") List<String> dealerCodes);

    List<Position> findByEmployee(@Param("employeeId") String employeeId, @Param("roleFlag") boolean roleFlag);

    List<String> findIdByOrgIds(@Param("orgIds") Set<String> orgIds);

    List<Position> findByOrgId(@Param("orgId") String orgId);

    List<DictPrivilege> findDictPriByPosition(@Param("positionId") String positionId);

    List<DictPrivilege> findAreaPriByPosition(@Param("positionId") String positionId);

    //纵队管理数据权限控制会用到
    List<String> findIdByEmpAndOrg(@Param("empId") String empId, @Param("orgId") String orgId);

    //新增和修改时使用
    Position findAllById(String id);

    Position findById(@Param("id") String id);

    Position findBySeq(@Param("seq") String seq);

    List<String> findRoleRelation(@Param("positionId") String positionId);

    List<String> findXRoleRelation(@Param("positionId") String positionId);

    void updateStatus(@Param("id") String id, @Param("status") Boolean status);

    void updateStatusByIds(@Param("posIds") List<String> posIds, @Param("status") Boolean status);

    void updateStatusByOrgId(@Param("orgId") String orgId, @Param("status") Boolean status);

    void updateStatusByOrgIds(@Param("orgIds") Set<String> orgIds, @Param("status") Boolean status);

    void update(Position position);

    void updateDataPrivilege(@Param("positionId") String positionId,
                             @Param("dataPrivilege") DataPrivilege dataPrivilege);

//    void updateRole(@Param("posId") String id, @Param("roleId") String roleId);

    void deleteDictPrivilegeByPosition(@Param("positionId") String positionId);

    void deleteRoleRelation(@Param("positionId") String positionId);

    void deleteXRoleRelation(@Param("positionId") String positionId);

    void insert(Position position);

    void insertDictPrivilege(@Param("dictPrivileges") List<DictPrivilege> dictPrivileges);

    void insertRoleRelation(@Param("positionId") String positionId, @Param("roleIds") List<String> roleIds);

    void insertXRoleRelation(@Param("positionId") String positionId, @Param("xRoleIds") List<String> xRoleIds);

    List<Position> findByLevel(@Param("levelName") String levelName, @Param("companyCode") String companyCode);
}
