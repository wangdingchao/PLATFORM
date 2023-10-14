package com.example.platform.mapper;

import com.example.platform.pojo.EmpStatus;
import com.example.platform.pojo.Employee;
import com.example.platform.pojo.EmployeeDTO;
import com.example.platform.pojo.MiddleDealerEmployee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 作       者：zhaoxin
 * 最后修改时间：2018-3-20
 * 描      述：员工mapper
 */

@Mapper
public interface EmployeeMapper {

    Integer countByCondition(@Param("orgIds") Set<String> orgIds,
                             @Param("positionId") String positionId,
                             @Param("name") String name,
                             @Param("mobile") String mobile,
                             @Param("companyCode") String companyCode,
                             @Param("statues") List<EmpStatus> statues);

    List<EmployeeDTO> findPageByCondition(@Param("orgIds") Set<String> orgIds,
                                          @Param("positionId") String positionId,
                                          @Param("name") String name,
                                          @Param("mobile") String mobile,
                                          @Param("companyCode") String companyCode,
                                          @Param("statues") List<EmpStatus> statues,
                                          @Param("offset") int offset,
                                          @Param("size") int size);

    List<EmployeeDTO> findListByCondition(@Param("organizationId") String organizationId,
                                          @Param("positionId") String positionId,
                                          @Param("name") String name,
                                          @Param("mobile") String mobile,
                                          @Param("statues") List<EmpStatus> statuses,
                                          @Param("companyCode") String companyCode,
                                          @Param("dealerCode") String dealerCode);

//    List<EmployeeDTO> findByOrgAndPos(@Param("orgPath") String orgPath,
//                                      @Param("isChildOrg") Boolean isChildOrg,
//                                      @Param("positionIds") List<String> positionIds,
//                                      @Param("isDimission") Boolean isDimission,
//                                      @Param("isTeamEmp") Boolean isTeamEmp,
//                                      @Param("isConnectorEmp") Boolean isConnectorEmp,
//                                      @Param("isConnector") Boolean isConnector,
//                                      @Param("isTroop") Boolean isTroop,
//                                      @Param("employeeId") String employeeId,
//                                      @Param("name") String name,
//                                      @Param("statues") List<EmpStatus> statuses,
//                                      @Param("sogal") Boolean sogal,
//                                      @Param("schmidt") Boolean schmidt,
//                                      @Param("milana") Boolean milana,
//                                      @Param("dealerCode") String dealerCode,
//                                      @Param("graduated") DesignerStatus graduated,
//                                      @Param("companyCodes") List<String> companyCodes);

    List<EmployeeDTO> findByMobiles(@Param("mobiles") List<String> mobiles,
                                    @Param("companyCode") String companyCode,
                                    @Param("empStatus") EmpStatus noEmpStatus);

    List<EmployeeDTO> findByLike(@Param("like") String like,
                                 @Param("statues") List<EmpStatus> statuses,
                                 @Param("companyCode") String companyCode);

    EmployeeDTO findByMobileAndDealer(@Param("mobile") String mobile,
                                      @Param("companyCode") String companyCode,
                                      @Param("statues") List<EmpStatus> statuses);

    EmployeeDTO findById(@Param("id") String id, @Param("statues") List<EmpStatus> statues, @Param("isAll") Boolean isAll);

    EmployeeDTO findByOrgAndId(@Param("id") String id, @Param("orgId") String orgId, @Param("statues") List<EmpStatus> statues);

    EmployeeDTO findByIdAndStatues(@Param("id") String id,@Param("statues") List<EmpStatus> statues);

    void updateStatus(@Param("id") String id, @Param("status") EmpStatus empStatus);

    int updateName(@Param("id") String id, @Param("name") String name);

    int updateMobile(@Param("id") String empId, @Param("mobile") String mobile);

    void insert(EmployeeDTO employee);

    List<EmployeeDTO> findEmpByDeptId(@Param("orgId") String orgId, @Param("valid") Boolean valid);

    Employee get(String id);

    List<Employee> gets(@Param("empIds") List<String> empIds);

    List<MiddleDealerEmployee> findMiddleDealerEmployees(@Param("empIds") List<String> empIds);

    List<MiddleDealerEmployee> findMiddleDealerEmployeesByEmpId(@Param("empId") String empId);

    List<EmployeeDTO> findEmpInfoByCondition(@Param("empId") String empId,@Param("orgId") String orgId);

    void update(Employee employee);

    void setStatus(@Param("toStatus") EmpStatus toStatus, @Param("fromStatus") EmpStatus fromStatus, @Param("id") String id);

    String getMaxCode();

    void updateTime(@Param("onBoardingTime") Date onBoardingTime, @Param("turnPositiveTime")Date turnPositiveTime, @Param("resignationDate")Date resignationDate, @Param("id") String id);

    int getEmpCountByOrgId(String organizationId);


    //清空所有等级
    void updateCleanGrade();

    EmployeeDTO findOrderLevelByEmp(@Param("orgId") String orgId, @Param("mobile") String mobile);

    List<EmployeeDTO> findOrgEmployee(@Param("orgPath") String orgPath,
                                      @Param("empStatuses") List<EmpStatus> empStatuses,
                                      @Param("orgId") String orgId,
                                      @Param("dealerCode") String dealerCode,
                                      @Param("companyCode") String companyCode);
}
