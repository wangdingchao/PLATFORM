
package com.example.platform.mapper;

import com.example.platform.pojo.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * 作       者：zhaoxin
 * 最后修改时间：2018-3-20
 * 描      述：组织机构mapper
 */
@Mapper
public interface OrganizationMapper {


    Set<String> findIdByCodePath(String code);

    String findCodePathById(@Param("id") String id);

    List<Organization> findByCondition(@Param("name") String name, @Param("city") String city,
                                       @Param("area") String area, @Param("shopIds") List<String> shopIds,
                                       @Param("sogal") boolean sogal, @Param("schmidt") boolean schmidt,
                                       @Param("milana") boolean milana, @Param("dealerCodes") List<String> dealerCodes,
                                       @Param("myOrgId") String myOrgId, @Param("companyCodes") List<String> companyCodes);

    List<Organization> findTreeByParentId(@Param("parentId") String parentId, @Param("dealerCode") String dealerCode);

    List<Organization> findOrgsForTree(@Param("parentId") String parentId, @Param("dealerCodes") List<String> dealerCodes,
                                       @Param("companyCode") String companyCode, @Param("queryAll") Boolean queryAll, @Param("valid") Boolean valid,
                                       @Param("orgName") String orgName);

    List<Organization> findByParentId(@Param("parentId") String parentId, @Param("dealerCodes") List<String> dealerCodes,
                                      @Param("offset") int offset, @Param("size") int size);

    List<Organization> findByCodePath(@Param("codePath") String codePath);

    Organization findById(@Param("id") String id);

    //不关注是否有效
    Organization findAllById(@Param("id") String id);

    Organization findByCode(@Param("code") String code);

    //联查询
    Organization findUnionById(@Param("id") String id);

    void insert(Organization organization);

    void updateOrganizationForDsp(Organization organization);

    void updateBaseOrganization(Organization organization);

    void updateLocation(@Param("id") String id, @Param("longitude") double longitude, @Param("latitude") double latitude);

    void updateStatus(@Param("id") String id, @Param("valid") Boolean valid);

    void updateSource(@Param("id") String id, @Param("source") String source);

    void updateStatusByIds(@Param("Ids") Set<String> Ids, @Param("valid") Boolean valid);

    void updatePathById(@Param("id") String id, @Param("path") String path);

    void updatePathByIdForDsp(@Param("id") String id, @Param("path") String path);

    int countByParentId(@Param("parentId") String parentId, @Param("dealerCodes") List<String> dealerCodes);

    //添加修改组织机构验证用，请不要乱用
    int countByCondition(@Param("code") String code, @Param("parentId") String parentId, @Param("name") String name);

//    List<DeptRelationship> findAllDeptRelationship();
//
//    List<EmpRelationship> findAllEmpRelationship();
//
//    DeptRelationship findDeptRelationshipByOrg(@Param("orgId") String orgId);

    String findDingDeptId(@Param("orgId") String orgId);

    int addDeptRelationship(@Param("dingDept") String dingDept, @Param("orgId") String orgId, @Param("wecomDeptId") String wecomDeptId);

    List<Organization> findAllDept();

    Organization findCancelDingDept(@Param("id") String id);

    void deleteDingRelationship(@Param("dingDeptIds") List<String> dingDeptIds);

    void deleteDingRelationshipByOrg(@Param("deptIds") List<String> deptIds);

    void insertDeptRelationship(@Param("orgId") String orgId, @Param("dingDeptId") String dingDeptId);

    void updateDeptRelationship(@Param("orgId") String orgId, @Param("dingDeptId") String dingDeptId);

//    void insertOrganizationChannel(OrganizationChannel organizationChannel);
//
//    void updateOrganizationChannel(OrganizationChannel organizationChannel);
//
//    List<OrganizationChannel> findOrganizationChannel(@Param("orgId") String orgId);

    void deleteOrganizationChannel(@Param("ids") List<String> ids, @Param("orgId") String orgId);

//    void updateDealerOrganizationOrgTypeById(@Param("orgType") OrgType orgType, @Param("id") String id);

    int selectNextOrgNumByParentId(@Param("parentId") String parentId);

    void updateOrganizationShopInfoById(Organization organization);

//    List<Organization> selectOrgListByCondition(@Param("queryCondition") OrgQueryCondition queryCondition);

    //修改门店出款公司和账户
    void updateOrgCompanyAccount(Organization organization);

    void updateOrgBusinessSet(Organization organization);

    void updateDealerCodeByPath(@Param("codePath") String codePath, @Param("dealerCode") String dealerCode);

    void addErrorSyncDept(@Param("id") String id, @Param("orgId") String orgId, @Param("deptName") String deptName, @Param("casedBy") String casedBy);

//    List<DeptRelationship> findWecomDeptInfoByIds(@Param("ids") List<String> ids);

//    void insertDealerOrganizationBrand(DealerOrganizationBrand dealerOrganizationBrand);

//    List<DealerOrganizationBrand> findDealerOrganizationBrandByOrgId(@Param("orgId") String orgId);

    List<Organization> findSogalOrganizationByOrgId(@Param("orgId") String orgId, @Param("shopNum") String shopNum);

    List<Organization> findSchmidtOrganizationByOrgId(@Param("orgId") String orgId, @Param("shopNum") String shopNum);

    List<Organization> findMilanaOrganizationByOrgId(@Param("orgId") String orgId, @Param("shopNum") String shopNum);

//    @Select("<script>SELECT a.COMPANY_CODE, a.DEALER_CODE, a.ORG_ID, b.NAME AS ORG_NAME, a.BRAND_LABEL, a.DEALER_CONFIG FROM DEALER_BRAND_LABEL a INNER JOIN DEALER_ORGANIZATION b ON a.ORG_ID = b.ID        \n" +
//            "<where>\n" +
//            "    <if test='companyCode != null'>\n" +
//            "        a.COMPANY_CODE = #{companyCode}\n" +
//            "    </if>\n" +
//            "</where>\n" +
//            "ORDER BY a.DEALER_CODE ASC</script>")
//    List<DealerBrandLabel> findDealerBrandLabel(@Param("companyCode") String companyCode);

}
