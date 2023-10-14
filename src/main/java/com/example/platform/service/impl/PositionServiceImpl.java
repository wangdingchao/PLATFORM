
package com.example.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.platform.Constants;
import com.example.platform.mapper.AuthMapper;
import com.example.platform.mapper.OrganizationMapper;
import com.example.platform.mapper.PositionMapper;
import com.example.platform.pojo.*;
import com.example.platform.pojo.constants.ComFinalParams;
import com.example.platform.pojo.enums.DataPrivilege;
import com.example.platform.service.IPositionService;
import com.example.platform.utils.JedisUtil;
import com.example.platform.utils.JsonUtil;
import com.example.platform.service.util.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 作       者：zhaoxin
 * 最后修改时间：2018-3-22
 * 描      述：职位service实现类
 */
@Slf4j
@Service
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private OrganizationMapper organizationMapper;
//    @Autowired
//    private CommonOrganizationMapper CommonOrganizationMapper;
//    @Autowired
//    private TeamMapper teamMapper;
//    @Autowired
//    private EmployeeOrganizationMapper employeeOrganizationMapper;
//    @Autowired
//    private ContentMapper contentMapper;
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private ValidateUtil validateUtil;
//    @Autowired
//    private PositionPrivilegeOrgRelationMapper privilegeOrgRelationMapper;

//    @Autowired
//    private SyncEmpOrgFromFileService syncEmpOrgFromFileService;
//    @Autowired
//    private EmployeeFileLogMapper employeeFileLogMapper;
//
//    @Override
//    @Transactional(rollbackFor = {BizException.class, Throwable.class})
//    @ExceptionSendDingTalk(webHook = "${ding.talk.web.hook.oa}")
//    public void insertOrUpdate(Position position) throws BizException {
////        if (StringUtils.isBlank(position.getId())) {
////            position.setId(StringUtil.uuid());
////        }
////
////        //根据id查询职位
////        Position before = positionMapper.findAllById(position.getId());
////
////        Organization org = organizationMapper.findById(position.getOrgId());
////        if (org == null) {
////            throw new BizException(DealerErrorCode.ORGANIZATION_IS_NULL, position.getOrgId());
////        }
////        position.setCompanyCode(org.getCompanyCode());
////        //如果职位不存在，则新增
////        if (before == null) {
////            validateUtil.paramNotNull(position.getName(), position.getSeq(), position.getOrgId(), position.getCompanyCode());
////            position.setValid(true);
////            position.setDataPrivilege(DataPrivilege.ALL);
////
////            Position posSeq = positionMapper.findBySeq(position.getSeq());
////            if (posSeq != null) {
////                log.warn(Constants.SERVICE_LOG_TAG + " position seq is exist, {}", position);
////                throw new BizException(DealerErrorCode.POSITION_SEQ_ERROR);
////            }
////
////            positionMapper.insert(position);
////            syncEmpOrgFromFileService.addPosition(position.getId());
////            return;
////        }
////        syncEmpOrgFromFileService.updatePosition(before.getOrgId(), position);
////        //如果职位存在，则修改
////        positionMapper.update(position);
////
////        //清除数据权限缓存
////        List<EmployeeOrganization> employeeOrganizations = employeeOrganizationMapper.findByCondition(null,
////                null, position.getId());
////        for (EmployeeOrganization employeeOrganization : employeeOrganizations) {
////            jedisUtil.del(ComFinalParams.PRIVILEGE_TAG + employeeOrganization.getOrgId() + ":" + position.getId());
////        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = {BizException.class, Throwable.class})
//    public void addPositionAuthorize(PositionPower position) throws BizException {
//        String positionId = position.getId();
//        Position pos = positionMapper.findById(positionId);
//        validateUtil.ObjectNotNull(pos, Position.class, DealerErrorCode.POSITION_ID_NULL);
//
//        //数据权限
//        if (position.getDataPrivilege() != null) {
//            positionMapper.updateDataPrivilege(positionId, position.getDataPrivilege());
//        }
//
//        //业务权限
//        List<DictPrivilege> dictPrivileges = position.getDictPrivileges();
//        positionMapper.deleteDictPrivilegeByPosition(positionId);
//        if ((dictPrivileges != null) && !dictPrivileges.isEmpty()) {
//            for (DictPrivilege dictPrivilege : dictPrivileges) {
//                dictPrivilege.setId(StringUtil.uuid());
//                dictPrivilege.setPositionId(positionId);
//            }
//            positionMapper.insertDictPrivilege(dictPrivileges);
//        }
//
//        //模块数据权限
//        authMapper.deleteDataPower(positionId);
//        if ((position.getDataPowers() != null) && !position.getDataPowers().isEmpty()) {
//            for (DataPower dataPower : position.getDataPowers()) {
//                dataPower.setPositionId(positionId);
//            }
//            authMapper.addDataPower(position.getDataPowers());
//        }
//
//        //dsp角色
////        positionMapper.updateRole(positionId, position.getRoleId());
//        positionMapper.deleteRoleRelation(positionId);
//        List<String> roleIds = position.getRoleIds();
//        if (null != roleIds && !roleIds.isEmpty()) {
//            positionMapper.insertRoleRelation(positionId, roleIds);
//        }
//
//        //xplan角色
//        positionMapper.deleteXRoleRelation(positionId);
//        List<String> xRoleIds = position.getXRoleIds();
//        if (null != xRoleIds && !xRoleIds.isEmpty()) {
//            positionMapper.insertXRoleRelation(positionId, xRoleIds);
//        }
//
//        //更新权限修改时间
//        Long time = new Date().getTime();
//        List<EmployeeOrganization> condition = employeeOrganizationMapper.findByCondition(null, null,
//                position.getId());
//        for (EmployeeOrganization emp : condition) {
//            jedisUtil.set(ComFinalParams.AUTHORIZE_TAG + emp.getEmpId(), time.toString());
//
//            jedisUtil.del(ComFinalParams.PRIVILEGE_TAG + emp.getOrgId() + ":" + emp.getPositionId());
//        }
//    }
//
//    @Override
//    public PagedList<Position> findPageByCondition(String name, String alias, String orgId, Boolean valid, List<String> label,
//                                                   List<String> areaCodes, DataPrivilege privilege, Integer page, Integer size) throws BizException {
//        if ((label == null) || label.isEmpty()) {
//            label = null;
//        }
//        PageInfo pageInfo = new PageInfo(page, size);
//        Set<String> set = validateUtil.getChildOrg(orgId, privilege);
//        int totalSize = positionMapper.countByCondition(name, alias, set, valid, label, areaCodes);
//        List<Position> positions = positionMapper.findPageByCondition(name, alias, set, valid,
//                label, areaCodes, pageInfo.getOffset(), pageInfo.getSize());
//        positions = this.getLabel(positions);
//        return new PagedList<>(positions, totalSize);
//    }
//
//    @Override
//    public List<Position> findListByPosition(String name, String alias, String orgId, Boolean valid, List<String> areaCodes) {
//        return positionMapper.findListByPosition(name, alias, orgId, valid, areaCodes);
//    }
//
//    @Override
//    public List<Position> getParentSubsetAllPosition(String name, String orgId, List<String> areaCodes) {
//        Organization organization = organizationMapper.findAllById(orgId);
//        Set<String> set = new HashSet<>();
//        if (organization != null) {
//            set = validateUtil.getChildOrg(organization.getParentId(), DataPrivilege.WEDEPART);
//        }
//        return positionMapper.findPageByCondition(name, null, set, true,
//                null, areaCodes, 0, 200);
//    }
//
//    @Override
//    public Position findBySeq(String seq) {
//        Position position = positionMapper.findBySeq(seq);
//        if (position == null) {
//            log.info(Constants.SERVICE_LOG_TAG + " finding Position, seq is wrong. \n seq: {}", seq);
//            return null;
//        }
//        List<DictPrivilege> dictPrivileges = positionMapper.findDictPriByPosition(position.getId());
//        position.setDictPrivileges(dictPrivileges);
//        return position;
//    }
//
//    public Position findById(String id) {
//        Position position = positionMapper.findById(id);
//        if (position == null) {
//            log.info("职位不存在， id = {}", id);
//            return null;
//        }
//        //业务权限
//        List<DictPrivilege> dictPrivileges = positionMapper.findDictPriByPosition(position.getId());
//        position.setDictPrivileges(dictPrivileges);
//
//        //模块权限
//        List<DataPower> dataPowers = authMapper.findDataPowerByPositionId(position.getId());
//        position.setDataPowers(dataPowers);
//
//        //dsp角色集合
//        List<String> roleIds = positionMapper.findRoleRelation(id);
//        position.setRoleIds(roleIds);
//
//        //xplan角色集合
//        List<String> xRoleIds = positionMapper.findXRoleRelation(id);
//        position.setXRoleIds(xRoleIds);
//
//        return position;
//    }

    @Override
    public PositionPower findPositionPower(String empId, String positionId, String orgId, List<String> dictTypes) {
        PositionPower result = new PositionPower();
        PositionPower positionPower = new PositionPower();
        Position position = positionMapper.findById(positionId);
        if (position == null) {
            log.warn(Constants.SERVICE_LOG_TAG + "员工职位不存在, positionId = {}", positionId);
            return null;
        }
        Organization organization = organizationMapper.findById(orgId);
        if (organization == null) {
            log.warn(Constants.SERVICE_LOG_TAG + "员工组织机构不存在, orgId = {}", orgId);
            return null;
        }

        //数据权限
        Set<String> resultIds = this.getResultIds(position.getDataPrivilege(), empId, orgId, organization, positionId,"ALL");
        positionPower.setDataPrivilege(position.getDataPrivilege());
        positionPower.setResultIds(resultIds);
        result.setDataPrivilege(position.getDataPrivilege());
        result.setResultIds(resultIds);

        //模块数据权限
        List<DataPower> dataPowers = authMapper.findDataPowerByPositionId(positionId);
        List<DataPower> dataPowersResult = new ArrayList<>();
        for (DataPower dataPower : dataPowers) {
            Set<String> ids = this.getResultIds(dataPower.getDataPrivilege(), empId, orgId, organization, positionId, dataPower.getServiceType().getCode());
            dataPower.setResultIds(ids);
            dataPowersResult.add(dataPower);
        }
        positionPower.setDataPowers(dataPowers);
        dataPowersResult = (dataPowersResult.size() > 0) ? dataPowersResult : dataPowers;
        result.setDataPowers(dataPowersResult);

        //业务权限
        List<DictPrivilege> dictPriByPosition = positionMapper.findDictPriByPosition(positionId);
        positionPower.setDictPrivileges(dictPriByPosition);
        List<DictPrivilege> dictPrivilegesResult = null;
        if (dictTypes != null) {
            dictPrivilegesResult = new ArrayList<>();
            for (String dictType : dictTypes) {
                for (DictPrivilege dictPrivilege : dictPriByPosition) {
                    if (dictType.equals(dictPrivilege.getDictType().getCode())) {
                        dictPrivilegesResult.add(dictPrivilege);
                    }
                }
            }
        }
        dictPrivilegesResult = (dictPrivilegesResult != null) ? dictPrivilegesResult : dictPriByPosition;
        result.setDictPrivileges(dictPrivilegesResult);

        String json = JsonUtil.beanToJson(positionPower);
        try {
            jedisUtil.hash(ComFinalParams.PRIVILEGE_TAG + orgId + ":" + positionId, empId, json, 86400);
        } catch (Throwable e) {
            log.warn(Constants.SERVICE_LOG_TAG + " redis connection fail");
        }

        return result;
    }
//
//    @Override
//    public PositionPower findAreaPower(String positionId, String orgId, int chargeType, EmployeeDTO employee ) {
//        //chargeType  0:经销商划分 1:公司划分 2:全部公司 3:经销商经营的品牌
//        List<DictPrivilege> resultPower = new ArrayList<>();
//        Organization org = organizationMapper.findById(orgId);
//        if (0 == chargeType) {
//            if (StringUtils.isNotBlank(jedisUtil.get(ComFinalParams.COMPANY_SWITCH + employee.getId()))) {
//                String companyCode = jedisUtil.get(ComFinalParams.COMPANY_SWITCH + employee.getId());
//                // 切换公司后对应的所有经销商
//                List<String> dealerCodes = CommonOrganizationMapper.companyDealerCodes(companyCode);
//                for (String code : dealerCodes) {
//                    DictPrivilege dictPrivilege = new DictPrivilege();
//                    dictPrivilege.setDictValue(code);
//                    dictPrivilege.setDictLabel(AreaPrivilege.find(code).getDealerName());
//                    resultPower.add(dictPrivilege);
//                }
//            } else {
//                if (StringUtils.isBlank(org.getDealerCode())) {
//                    //公司对应的所有经销商
//                    List<String> dealerCodes = CommonOrganizationMapper.companyDealerCodes(org.getCompanyCode());
//                    for (String code : dealerCodes) {
//                        DictPrivilege dictPrivilege = new DictPrivilege();
//                        dictPrivilege.setDictValue(code);
//                        dictPrivilege.setDictLabel(AreaPrivilege.find(code).getDealerName());
//                        resultPower.add(dictPrivilege);
//                    }
//                } else {
//                    DictPrivilege dictPrivilege = new DictPrivilege();
//                    dictPrivilege.setDictValue(org.getDealerCode());
//                    dictPrivilege.setDictLabel(AreaPrivilege.find(org.getDealerCode()).getDealerName());
//                    resultPower.add(dictPrivilege);
//                }
//            }
//            //获取机构所属经销商
//            List<String> extraDealerPrivileges = CommonOrganizationMapper.selectExtraDealerPrivilege(orgId);
//            if (CollectionUtils.isNotEmpty(extraDealerPrivileges)) {
//                List<String> strs = resultPower.stream().map(DictPrivilege::getDictValue).collect(Collectors.toList());
//                for (String extraDealerPrivilege : extraDealerPrivileges) {
//
//                    if (!strs.contains(extraDealerPrivilege)) {
//                        DictPrivilege dictPrivilege = new DictPrivilege();
//                        dictPrivilege.setDictValue(extraDealerPrivilege);
//                        dictPrivilege.setDictLabel(AreaPrivilege.find(extraDealerPrivilege).getDealerName());
//                        resultPower.add(dictPrivilege);
//                        strs.add(extraDealerPrivilege);
//                    }
//                }
//            }
//        } else if (1 == chargeType) {
//            String companyCode = org.getCompanyCode();
//            if (StringUtils.isNotBlank(jedisUtil.get(ComFinalParams.COMPANY_SWITCH + employee.getId()))) {
//                companyCode = jedisUtil.get(ComFinalParams.COMPANY_SWITCH + employee.getId());
//            }
//            DictPrivilege dictPrivilege = new DictPrivilege();
//            dictPrivilege.setDictValue(org.getCompanyCode());
//            dictPrivilege.setDictLabel(Company.find(companyCode).getMessage());
//            resultPower.add(dictPrivilege);
//        } else if (2 == chargeType) {
//            for (Company company : Company.values()) {
//                DictPrivilege dictPrivilege = new DictPrivilege();
//                dictPrivilege.setDictValue(company.getCode());
//                dictPrivilege.setDictLabel(company.getMessage());
//                resultPower.add(dictPrivilege);
//            }
//        } else if (3 == chargeType) {
//            if (StringUtils.isBlank(org.getDealerCode()))
//                return null;
//
//            String strJson = jedisUtil.get(ComFinalParams.DEALER_ORG_TAG + orgId);
//            if (StringUtils.isNotBlank(strJson)) {
//                DealerOrg dealerOrg = JsonUtil.stringToBean(strJson, new TypeReference<DealerOrg>() {
//                });
//                if (StringUtils.isBlank(dealerOrg.getBrandLabel()))
//                    return null;
//
//                for (String brandLabel : dealerOrg.getBrandLabel().split(",")) {
//                    DictPrivilege dictPrivilege = new DictPrivilege();
//                    dictPrivilege.setDictValue(brandLabel);
//                    dictPrivilege.setDictLabel(BrandLabel.getBrandByCode(brandLabel).getMessage());
//                    resultPower.add(dictPrivilege);
//                }
//            }
//        }
//
//
//        DataPrivilege areaPrivilege = resultPower.size() > 1 ? DataPrivilege.ALL : DataPrivilege.I;
//
//        PositionPower result = new PositionPower();
//        result.setAreaPrivilege(areaPrivilege);
//        result.setDictPrivileges(resultPower);
//        return result;
//    }
//
//    @Override
//    @Transactional(rollbackFor = {BizException.class, Throwable.class})
//    @ExceptionSendDingTalk(webHook = "${ding.talk.web.hook.oa}")
//    public void updateStatus(String id, Boolean status) throws BizException {
////        Position position = positionMapper.findAllById(id);
////        if (!status) {
////            if (position == null) {
////                throw new BizException(DealerErrorCode.POSITION_ID_NULL);
////            }
////            List<EmployeeOrganization> employeeOrganizations = employeeOrganizationMapper.findByPositionIds(Arrays.asList(id));
////            if (!employeeOrganizations.isEmpty()) {
////                throw new BizException(DealerErrorCode.POSITION_EXIST_EMP);
////            }
////        } else {
////            Organization organization = organizationMapper.findAllById(position.getOrgId());
////            if (!organization.getValid()) {
////                throw new BizException(DealerErrorCode.POSITION_SET_ENABLE_ERROR, "该岗位所在部门已停用");
////            }
////        }
////        positionMapper.updateStatus(id, status);
////        syncEmpOrgFromFileService.setEnablePosition(id, status);
//    }
//
//    @Override
//    public List<com.sogal.common.domain.user.Position> findByEmployee(String employeeId) throws BizException {
//        List<com.sogal.common.domain.user.Position> positions = positionMapper.findByEmployee(employeeId, false);
//        if (positions == null || positions.isEmpty()) {
//            log.info(Constants.SERVICE_LOG_TAG + " no find position by employee, employeeId = {}", employeeId);
//            throw new BizException(DealerErrorCode.POSITION_ID_NULL);
//        }
//
//        //查询职位标签
//        for (com.sogal.common.domain.user.Position position : positions) {
//            List<Tag> tags = contentMapper.listTagOfRealm(Realm.POSITION, position.getPositionId());
//            position.setTags(tags);
//        }
//        return positions;
//    }
//
//    @Override
//    public List<EmpFileOperateLogVO> getOperationLog(String yearMonth, List<String> scenes) throws BizException {
//        if (CollectionUtils.isEmpty(scenes)) {
//            scenes = Arrays.asList(PositionOperateLogHandler.DISABLE_OR_ENABLE_POST, PositionOperateLogHandler.MODIFY_POST);
//        }
//        return employeeFileLogMapper.getOperationLog(null, scenes,
//                ReqHolder.getLoginUserCompanyCode(), yearMonth);
//    }
//
//    /**
//     * 获取职位的标签
//     */
//    private List<Position> getLabel(List<Position> positions) throws BizException {
//        for (Position position : positions) {
//            List<Tag> tags = contentMapper.listTagOfRealm(Realm.POSITION, position.getId());
//            position.setTags(tags);
//            List<DataPower> dataPowers = authMapper.findDataPowerByPositionId(position.getId());
//            position.setDataPowers(dataPowers);
//        }
//        return positions;
//    }
//
    private Set<String> getResultIds(DataPrivilege dataPrivilege, String empId, String orgId, Organization organization,
                                     String positionId, String serviceType) {
        Set<String> resultIds = new HashSet<>();
        switch (dataPrivilege) {
            case I:
                resultIds.add(empId);
                break;
            case ALL:
                break;
            case DEPARTMENT:
                resultIds.add(orgId);
                break;
            case WE:
                if (StringUtils.isBlank(organization.getMilanaCode())
                        && StringUtils.isBlank(organization.getSchmidtCode())
                        && StringUtils.isBlank(organization.getSogalCode())) {
                    List<EmployeeOrganization> condition = new ArrayList<>();
//                            employeeOrganizationMapper.findByCondition(null, orgId, null);
                    Set<String> set = new HashSet<>();
                    for (EmployeeOrganization relation : condition) {
                        set.add(relation.getEmpId());
                    }
                    resultIds.addAll(set);
                    resultIds.add(empId);
                } else {
                    resultIds = new HashSet<>();
//                            teamMapper.findEmpIdByParentId(empId, orgId);
                    List<String> list = new ArrayList(resultIds);
                    if (!list.isEmpty()) {
                        for (String id : list) {
                            Set<String> emp = new HashSet<>();
//                                    teamMapper.findEmpIdByParentId(id, orgId);
                            resultIds.addAll(emp);
                        }
                    }
                    resultIds.add(empId);
                }
                break;
            case WEDEPART:
                resultIds = organizationMapper.findIdByCodePath(organization.getCodePath());
                resultIds.add(orgId);
                break;
            case WEANDASSIGNDEPART:
                resultIds = organizationMapper.findIdByCodePath(organization.getCodePath());
                resultIds.add(orgId);
                List<String> extraOrgIds = new ArrayList<>();
//                        privilegeOrgRelationMapper.selectExtraOrgIdsByPositionIdAndServiceType(positionId, serviceType);
                if(CollectionUtils.isNotEmpty(extraOrgIds)){
                    resultIds.addAll(extraOrgIds);
                }
                break;
            default:
                break;
        }
        return resultIds;
    }
}
