
package com.example.platform.service;

import com.example.platform.dto.PagedList;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.EmployeeDTO;
import com.example.platform.pojo.Position;
import com.example.platform.pojo.PositionPower;
import com.example.platform.pojo.enums.DataPrivilege;

import java.util.List;

/**
 * 作       者：zhaoxin
 * 最后修改时间：2018-3-21
 * 描      述：职位管理service
 */
public interface IPositionService {

    //根据是否有id进行插入或更新数据
    void insertOrUpdate(Position position) throws BizException;

    //根据id进行查询
    Position findById(String id);

    //根据序号进行查询
    Position findBySeq(String seq);

    //根据条件分页查询职位
    PagedList<Position> findPageByCondition(String name, String alias, String orgId, Boolean valid, List<String> label,
                                            List<String> areaCodes, DataPrivilege privilege, Integer page, Integer size) throws BizException;

    //根据条件查询职位list
    List<Position> findListByPosition(String name, String alias, String orgId, Boolean valid, List<String> areaCodes);

    //根据条件查询职位list
    List<Position> getParentSubsetAllPosition(String name, String orgId, List<String> areaCodes);

    //给职位添加业务权限和数据权限
    void addPositionAuthorize(PositionPower position) throws BizException;

    //根据员工id，职位id，组织机构id查询员工的数据权限和业务权限
    PositionPower findPositionPower(String empId, String positionId, String orgId, List<String> dictTypes);

    PositionPower findAreaPower(String positionId, String orgId, int chargeType, EmployeeDTO employee);

    //根据职位id修改职位状态
    void updateStatus(String id, Boolean status) throws BizException;

    //根据员工id查询员工职位
    List<Position> findByEmployee(String employeeId) throws BizException;

//    List<EmpFileOperateLogVO> getOperationLog(String yearMonth, List<String> scenes) throws BizException;
}
