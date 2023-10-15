package com.example.platform.service.util;

import com.example.platform.Constants;
import com.example.platform.error.GlobalErrorCode;
import com.example.platform.error.ValidateClass;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.EmployeeDTO;
import com.example.platform.pojo.PositionPower;
import com.example.platform.pojo.constants.ComFinalParams;
import com.example.platform.service.IPositionService;
import com.example.platform.utils.JedisUtil;
import com.example.platform.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 作       者：zhaoxin
 * 最后修改时间：2018-3-21
 * 描      述：经销商管理的验证类
 */
@Component
@Slf4j
public class ValidateUtil extends ValidateClass {

//    @Autowired
//    @Qualifier("rabbitmqPublish")
//    private RabbitmqPublish rabbitmqPublish;
//    @Autowired
//    private OrganizationMapper organizationMapper;
//    @Autowired
//    private IPositionService positionService;
    @Autowired
    private JedisUtil jedisUtil;

    public ValidateUtil() {
        this.setLogTag(Constants.SERVICE_LOG_TAG);
    }

//    private void sendMq(Map<String, String> params, MessageType messageType, ButtTeamType buttTeamType) {
//        MessageBody messageBody = new MessageBody();
//        if (buttTeamType != null) {
//            messageBody.setButtTeamType(buttTeamType);
//        }
//        messageBody.setType(messageType);
//        messageBody.setServiceName(Constants.SERVICE_NAME);
//        messageBody.setParams(params);
//        String json = JsonUtil.beanToJson(messageBody);
//        try {
//            log.debug("messageBody = " + messageBody);
////            rabbitmqPublish.publishMessage(json);
//        } catch (Throwable e) {
//            log.error("发送MQ消息失败，messageBody = " + json, e);
//        }
//        log.debug("发送消息成功 ：messageBody = " + messageBody);
//    }
//
//    void sendMq(Map<String, String> params, MessageType messageType) {
//        this.sendMq(params, messageType, null);
//    }
//
//    void sendMq(ButtTeamType buttTeamType, String empId, String orgId, String beforeTeamId, String nowTeamId) {
//        this.sendMq(buttTeamType, empId, orgId, beforeTeamId, nowTeamId, null);
//    }
//
//    void sendMq(ButtTeamType buttTeamType, String empId, String orgId, String beforeTeamId, String nowTeamId, String date) {
//        Map<String, String> params = new HashMap<>();
//        if (empId != null) {
//            params.put("empId", empId);
//        }
//        if (orgId != null) {
//            params.put("orgId", orgId);
//        }
//        if (beforeTeamId != null) {
//            params.put("beforeTeamId", beforeTeamId);
//        }
//        if (nowTeamId != null) {
//            params.put("nowTeamId", nowTeamId);
//        }
//        if (date != null) {
//            params.put("date", date);
//        }
//        this.sendMq(params, MessageType.BUTT_TEAM, buttTeamType);
//    }

//    public Set<String> getChildOrg(String orgId, DataPrivilege privilege) {
//        Set<String> set = new HashSet<>();
//        if (null != orgId) {
//            if (DataPrivilege.DEPARTMENT.equals(privilege)) {
//                set.add(orgId);
//            } else {
//                Organization organization = organizationMapper.findById(orgId);
//                if (null != organization) {
//                    set = organizationMapper.findIdByCodePath(organization.getCodePath());
//                    set.add(orgId);
//                }
//            }
//        }
//        return set;
//    }

    /*
    获取权限
     */
//    public PositionPower getPrivilege(EmployeeDTO employee) throws BizException {
//        try {
//            String strJson = jedisUtil.getHashField(ComFinalParams.PRIVILEGE_TAG + employee.getOrgId() + ":"
//                    + employee.getPositionId(), employee.getId());
//            PositionPower positionPower = JsonUtil.stringToBean(strJson, new TypeReference<PositionPower>() {
//            });
//            return positionPower;
//        } catch (Throwable e) {
//            log.error(Constants.SERVICE_LOG_TAG + " 未找到对应人员权限数据", e);
//            return null;
//        }
//    }

    public PositionPower getPriviliges(EmployeeDTO employee) throws BizException {
        try {
            String strJson = jedisUtil.getHashField(ComFinalParams.PRIVILEGE_TAG + employee.getOrganizationId() + ":"
                    + employee.getPositionId(), employee.getId());
            return JsonUtil.stringToBean(strJson, new TypeReference<PositionPower>() {
            });
        } catch (Throwable e) {
//            PositionPower positionPower = positionService.findPositionPower(employee.getId(), employee.getPositionId(), employee.getOrganizationId(),
//                     null);
//
//            if (null == positionPower) {
//                log.error(Constants.SERVICE_LOG_TAG + ":用户权限不足,错误码={},错误位置:channel-ValidateUtil-getPriviliges,"
//                        , GlobalErrorCode.USER_NOT_PRIVILEGE);
//                throw new BizException(GlobalErrorCode.USER_NOT_PRIVILEGE);
//            }
            return null;
        }
    }

//    public List<String> getBusinessPrivilege(PositionPower privilege, DictPrivilegeType type) throws BizException {
//        if (privilege != null) {
//            Map<String, List<DictPrivilege>> labelDict = privilege.getLabelDict();
//            if (labelDict == null || labelDict.isEmpty()) {
//                return null;
//            }
//            List<DictPrivilege> dictPrivileges = labelDict.get(type.getCode());
//            if (null == dictPrivileges || dictPrivileges.isEmpty()) {
//                return null;
//            }
//            List<String> dictPrivilegeList = new ArrayList<String>();
//            for (DictPrivilege dictPrivilege : dictPrivileges) {
//                dictPrivilegeList.add(dictPrivilege.getDictValue());
//            }
//            ;
//            return dictPrivilegeList;
//        }
//        return null;
//    }

}
