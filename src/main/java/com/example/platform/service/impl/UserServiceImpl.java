package com.example.platform.service.impl;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.platform.Constants;
import com.example.platform.error.GlobalErrorCode;
import com.example.platform.exception.BizException;
import com.example.platform.mapper.*;
import com.example.platform.pojo.*;
import com.example.platform.pojo.constants.FinalParams;
import com.example.platform.pojo.enums.MenuScope;
import com.example.platform.pojo.enums.PrivilegeErrorCode;
import com.example.platform.pojo.enums.Realm;
import com.example.platform.pojo.enums.TerminalType;
import com.example.platform.service.IUserService;
import com.example.platform.service.util.ValidateUtil;
import com.example.platform.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-1-22
 * 描述：user表service的实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthMapper privilegeMapper;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private CommonTerminalMapper terminalMapper;
//    @Autowired
//    private CommonCooperatorMapper cooperatorMapper;
//    @Autowired
//    private MessageServiceClient messageServiceClient;
//    @Autowired
//    private AuthMapper authMapper;
//    @Autowired
//    private ContentMapper contentMapper;
//
//    @Autowired
//    private GzhAccessTokenMapper accessTokenMapper;
//
    @Autowired
    private ValidateUtil validateUtil;
//    @Autowired
//    private JedisUtil jedisUtil;
//    @Autowired
//    private WxUtil wxUtil;
//    @Autowired
//    private EmployeeFileLogMapper employeeFileLogMapper;
//
//    @Autowired
//    private MpUserMapper mpUserMapper;
//    @Autowired
//    private WecomService wecomService;
//
//    @Autowired
//    private OfficialAccountService officialAccountService;
//    @Autowired
//    private IPositionService positionService;
//
////    @Value(value = "${idsRequestURLPrefix}")
////    private String idsRequestURLPrefix;
//
//
//    @Autowired
//    private MiniAppConfig miniAppConfig;
//
//    @Autowired
//    private GzhAppConfig gzhAppConfig;
//

    @Override
    public UserDTO login(String mobile, String password, UserType type, UserLoginLog userLoginLog, TerminalType terminal,
                         MenuScope scope) throws BizException {
        //SHA加密
        password = this.SHAException(password);

        //查询用户
        UserDTO user = userMapper.login(mobile, password, type);
        if (user == null) {
            this.dealLoginFollow(mobile, userLoginLog, FinalParams.LOGIN_FAIL, terminal, type);
            log.info(Constants.SERVICE_LOG_TAG + " login fail, mobile = {}, password = {}", mobile, password);
            throw new BizException(PrivilegeErrorCode.USER_ERROR_CODE);
        }
        if (!user.getValid()) {
            this.dealLoginFollow(mobile, userLoginLog, FinalParams.LOGIN_FAIL, terminal, type);
            log.info(Constants.SERVICE_LOG_TAG + " login fail, mobile = {}, password = {}", mobile, password);
            throw new BizException(PrivilegeErrorCode.USER_IS_BAN);
        }
        //补全用户信息
        user = this.getPrivilegeAndRole(user, userLoginLog, terminal, scope, type);
        this.dealLoginFollow(mobile, userLoginLog, FinalParams.LOGIN_SUCCESS, terminal, type);
        log.info(Constants.SERVICE_LOG_TAG + " login success, mobile = {}, password = {}", mobile, password);

        // 获取可切换公司
        EmployeeDTO employee = new EmployeeDTO();
        employee.setId(user.getId());
        return user;
    }



    @Override
    public void idsLogout(String idsaccessToken) {
//        try {
//            HttpRequest post = HttpUtil.createPost(idsRequestURLPrefix + "/api/auth/oauth2/logout");
//            post.header("Authorization", "Bearer " + idsaccessToken).timeout(5000);
//            String body = post.execute().body();
//            JSONObject jsonObject = JSONUtil.parseObj(body);
//            if (200 == jsonObject.getInt("code")) {
//                log.debug("ids 登出成功");
//            } else {
//                log.debug("ids 登出失败");
//            }
//        } catch (Exception e) {
//            log.debug("ids 登出失败:{}", e.getMessage());
//        }
    }

//    @Override
//    public void updatePassword(String mobile, String oldPass, String newPass, UserType type) throws BizException {
//        //保存最初铭文密码
//        String inPass = newPass;
//
//        if (StringUtils.isEmpty(oldPass) || StringUtils.isEmpty(newPass)) {
//            log.warn(Constants.SERVICE_LOG_TAG + " password is null,oldPass = {}, newPass = {} ",
//                    oldPass, newPass);
//            throw new BizException(PrivilegeErrorCode.PASSWORD_IS_NULL);
//        }
//        //SHA加密
//        oldPass = this.SHAException(oldPass);
//
//        UserDTO user = userMapper.login(mobile, oldPass, type);
//        if (user == null || !user.getValid()) {
//            log.warn(Constants.SERVICE_LOG_TAG + " oldPass error, mobile = {}, oldPass = {}",
//                    mobile, oldPass);
//            throw new BizException(PrivilegeErrorCode.PASSWORD_IS_ERROR);
//        }
//
//        //SHA加密
//        newPass = this.SHAException(newPass);
//        user.setPassword(newPass);
//        userMapper.updatePassword(mobile, type, newPass);
//
//        //设置修改密码标识
//        Long date = new Date().getTime();
//        jedisUtil.set(ComFinalParams.UPDATE_PASSWORD_TAG + user.getId(), date.toString());
//    }
//
//    @Override
//    public UserDTO loginByMobile(String mobile, String code, String imageUrl, String wechatOpenId, UserType type,
//                                 UserLoginLog userLoginLog, TerminalType terminal, MenuScope scope) throws BizException {
//        JsonResult result = messageServiceClient.checkCaptcha(FinalParams.LOGIN_CODE, mobile, code);
//        Boolean data = validateUtil.getJsonData(result, Boolean.class, "messageService-checkCaptcha");
//        if (!data) {
//            log.error("code is error, code = {}", code);
//            this.dealLoginFollow(mobile, userLoginLog, FinalParams.LOGIN_FAIL, terminal, type);
//            throw new BizException(GlobalErrorCode.CAPTCHA_ERROR);
//        }
//        UserDTO user = this.loginByMobile(mobile, userLoginLog, terminal, scope, imageUrl, wechatOpenId, null, type);
//        return user;
//    }
//
//    @Override
//    public UserDTO loginByMobile(String mobile, UserLoginLog userLoginLog, TerminalType terminal, MenuScope scope,
//                                 String imageUrl, String wechatOpenId, String unionId, UserType type) throws BizException {
//        UserDTO user = userMapper.findByMobile(mobile, type);
//        if (user == null) {
//            this.dealLoginFollow(mobile, userLoginLog, FinalParams.LOGIN_FAIL, terminal, type);
//            log.warn(Constants.SERVICE_LOG_TAG + "user find ,mobile is wrong. \n {}", mobile);
//            throw new BizException(PrivilegeErrorCode.USER_NOT_FIND);
//        }
//        if (!user.getValid()) {
//            this.dealLoginFollow(mobile, userLoginLog, FinalParams.LOGIN_FAIL, terminal, type);
//            log.info(Constants.SERVICE_LOG_TAG + " login fail, mobile = {}", mobile);
//            throw new BizException(PrivilegeErrorCode.USER_IS_BAN);
//        }
//
//        //补全返回用户信息
//        user = this.getPrivilegeAndRole(user, userLoginLog, terminal, scope, type);
//
//        //2023.03.15 dsp物流小程序，安装小程序，索小密统一用此接口
//        if (!StringUtils.isBlank(wechatOpenId)) {
//            // scope为空，默认索小密
//            if (null == scope){
//                scope = MenuScope.CRM;;
//            }
//            MiniAppUser miniAppUser = userMapper.findMiniAppUserByOpenId(wechatOpenId);
//            if (miniAppUser != null) {
//                if (!miniAppUser.getUserId().equals(user.getId())) {
//                    log.error("一个微信号只能绑定一个用户, before = {}, userId = {}", miniAppUser, user.getId());
//                    this.dealLoginFollow(mobile, userLoginLog, FinalParams.LOGIN_FAIL, terminal, type);
//                    throw new BizException(PrivilegeErrorCode.OPEN_ID_EXIST);
//                }
//            }else {
//                userMapper.insertMiniAppUser(new MiniAppUser(StringUtil.uuid(), user.getId(), wechatOpenId, unionId, imageUrl, scope));
//            }
//            user.setOpenId(wechatOpenId);
//            user.setUnionId(unionId);
//            user.setWechatOpenId(wechatOpenId);
//            //清除原来的formId数据
//            jedisUtil.del(ComFinalParams.FORM_ID_TAG + user.getId());
//        }
//        if(UserType.E.equals(user.getType())){
////            this.getIDSToken(mobile);
//            // 获取可切换公司
//            EmployeeDTO employee = new EmployeeDTO();
//            employee.setId(user.getId());
//            List<String> switchableCompanyCodes = dealerOrgUtil.listSwitchableCompanies(employee);
//            if (switchableCompanyCodes != null) {
//                if (StringUtils.isNotBlank(user.getCompanyCode())) {
//                    switchableCompanyCodes.add(user.getCompanyCode());
//                }
//                user.setSwitchableCompanyCodes(switchableCompanyCodes);
//            }
//        }
//        EmployeeDTO employee = new EmployeeDTO();
//        employee.setId(user.getId());
//        EmployeeDTO employeeDTO = dealerOrgUtil.listPageCompanyCodesAndDealerCodes(employee);
//        user.setInnerPageCompanyCodes(new LinkedHashSet<>(employeeDTO.getInnerPageCompanyCodes()));
//        user.setInnerPageDealerCodes(new LinkedHashSet<>(employeeDTO.getInnerPageDealerCodes()));
//        //处理登陆日志
//        this.dealLoginFollow(mobile, userLoginLog, FinalParams.LOGIN_SUCCESS, terminal, type);
//        return user;
//    }
//
//    @Override
//    public Map<String, Object> getToken(String code, String imageUrl, MenuScope scope, HttpServletRequest request) throws BizException {
//        return getToken(code,imageUrl,scope,"",request);
//    }
//
//    @Override
//    public Map<String, Object> getToken(String code, String imageUrl, MenuScope scope, String source, HttpServletRequest request) throws BizException {
//        Map<String, Object> map = new HashMap<>();
//        Result session = new Result();
//        if (MenuScope.WECOM.equals(scope)) {
//            // 企业微信的app进入,查询企微员工信息
//            Map<String, Object> tokenInfo = wecomService.getTokenInfo(code, scope.getCode());
//            if (null == tokenInfo || null == tokenInfo.get("userId")) {
//                log.error("get wecom access token happen error, errmsg ： 无法查询到企微员工信息");
//                throw new BizException("2555", "无法查询到企微员工信息");
//            }
//            return tokenInfo;
//        } else if(MenuScope.COOPERATION.equals(scope)||MenuScope.CFHL.equals(scope)){
//            Map<String, Object> resultMap = officialAccountService.loginByGZHCode(code, scope.getCode(), request);
//            return resultMap;
//        }else {
//            //获取openId
//            session = wxUtil.getLoginSession(code, miniAppConfig.judgeMiniAppId(scope), miniAppConfig.judgeMiniAppSecret(scope));
//            if (session.getOpenid() == null || session.getSession_key() == null) {
//                log.error("get access token happen error, errmsg = {}", session.getErrmsg());
//                throw new BizException(session.getErrcode(), session.getErrmsg());
//            }
//        }
//
//
//        String openId = session.getOpenid();
//        String unionId = session.getUnionid();
//        //根据openId查询有效用户
////        UserDTO user = userMapper.findByOpenId(openId, UserType.E);
//        MiniAppUser miniAppUser = userMapper.findMiniAppUserByOpenId(openId);
//
//
//        //其他返回
//        map.put("openId", openId);
//        map.put("unionId", unionId);
//
//        //用户存在，通过redis获取用户的token，token存在不为空,返回token
//        if (miniAppUser != null) {
//            map.put("userId", miniAppUser.getUserId());
//        }
//
//        //如果头像不为空，写入头像
//        if (!StringUtils.isBlank(imageUrl)) {
//            userMapper.updateImgByOpenId(imageUrl, openId);
//        }
//
//        //如果unionId不为空，写入头像
//        if (!StringUtils.isBlank(unionId)) {
//            userMapper.updateUnionId(unionId, openId);
//        }
//
//        return map;
//    }
//
//    @Override
//    public Map<String, String> getDecrypt(String code, String decryptData, String vi, MenuScope scope) throws BizException {
//        Map<String, String> map = new HashMap<>();
//        Result session = wxUtil.getLoginSession(code, miniAppConfig.judgeMiniAppId(scope), miniAppConfig.judgeMiniAppSecret(scope));
//        if (session.getOpenid() == null || session.getSession_key() == null) {
//            log.error("get access token happen error, errmsg = {}", session.getErrmsg());
//            throw new BizException(session.getErrcode(), session.getErrmsg());
//        }
//        String data = wxUtil.decryptData(decryptData, session.getSession_key(), vi);
//
//        map.put("openId", session.getOpenid());
//        map.put("data", data);
//        log.info("解密手机号返回：{}", map);
//        return map;
//    }
//
//    @Override
//    public void saveFormId(String formId, EmployeeDTO employee) {
//        Date day = DateUtil.getNDay(new Date(), 7);
//        long expire = day.getTime() - 5;
//        SendPrivilege sendPrivilege = new SendPrivilege(formId, expire);
//        String json = JsonUtil.beanToJson(sendPrivilege);
//        jedisUtil.rList(ComFinalParams.FORM_ID_TAG + employee.getId(), json);
//    }
//
//    @Override
//    public void resetPassword(String mobile, String onePass, String twoPass, String code, UserType type) throws BizException {
//        //记录铭文密码
//        String inPass = onePass;
//
//        JsonResult result = messageServiceClient.checkCaptcha(FinalParams.CHANGEPWS_CODE, mobile, code);
//        Boolean data = validateUtil.getJsonData(result, Boolean.class, "messageService-checkCaptcha");
//        if (!data) {
//            log.error("code is error, code = {}", code);
//            throw new BizException(GlobalErrorCode.CAPTCHA_ERROR);
//        }
//        //密码非空验证
//        if (StringUtils.isEmpty(onePass)) {
//            log.warn(Constants.SERVICE_LOG_TAG + "password is null");
//            throw new BizException(PrivilegeErrorCode.PASSWORD_IS_NULL);
//        }
//        //判断2次密码是否一致
//        if (!onePass.equals(twoPass)) {
//            log.warn(Constants.SERVICE_LOG_TAG + " two password is difference, one = {}, two = {}",
//                    onePass, twoPass);
//            throw new BizException(PrivilegeErrorCode.TWO_PASSWORD_DIFFERENCE);
//        }
//
//        //判断用户是否存在
//        UserDTO user = userMapper.findByMobile(mobile, type);
//        if (user == null) {
//            log.warn(Constants.SERVICE_LOG_TAG + " mobile is not exist, mobile = ", mobile);
//            throw new BizException(GlobalErrorCode.USER_NOT_EXIST);
//        }
//        //密码SHA加密并存入用户对象
//        onePass = this.SHAException(onePass);
//        user.setPassword(onePass);
//        //修改密码
//        userMapper.updatePassword(mobile, type, onePass);
//        //设置修改密码标识
//        Long date = new Date().getTime();
//        jedisUtil.set(ComFinalParams.UPDATE_PASSWORD_TAG + user.getId(), date.toString());
//    }
//
//    @Override
//    public Map<String, Object> findPrivilege(String userId, MenuScope scope) throws BizException {
//        Map<String, Object> map = new HashMap<>();
//        if(MenuScope.CFHL.equals(scope)){
//            map = officialAccountService.getGzhNewPrivilegeByUserIdAndScope(userId, scope.getCode());
//        }else {
//            UserDTO user = userMapper.findById(userId);
//            if (user == null) {
//                log.error("用户不存在， userId = {}", userId);
//                return null;
//            }
//            //获取数据权限
//            List<Position> positions = this.getPosition(user, null, null, user.getType());
//            map.put("bizPrivilege", positions);
//            //获取功能权限
//            List<String> menuPrivilege = privilegeMapper.findByUser(userId, scope);
//            map.put("menuPrivilege", menuPrivilege);
//            CallCenterAgent callCenterAgent = privilegeMapper.findByAgent(userId);
//            map.put("callCenterAgent", callCenterAgent);
//            if (scope == null) {
//                for (MenuScope menuScope : MenuScope.values()) {
//                    List<String> scopePrivilege = privilegeMapper.findByUser(userId, menuScope);
//                    map.put(menuScope.getCode(), scopePrivilege);
//                }
//            }
//        }
//
//        String time = jedisUtil.get(ComFinalParams.AUTHORIZE_TAG + userId);
//        map.put("newPrivilegeTime", time);
//        return map;
//    }
//
//    @Override
//    public void updateStatus(String id, boolean status) {
//        userMapper.updateStatus(id, status);
////        if (!status) {
////            //设置用户禁用标识
////            Long date = new Date().getTime();
////            jedisUtil.set(ComFinalParams.DISABLE_USER_TAG + id, date.toString());
////            syncEmpOrgFromUserService.resignSync(id, null);
////        } else {
////            //同步第三方人员
////            syncEmpOrgFromUserService.addEmpSync(id);
////        }
//    }
//
//    @Override
//    public UserDTO findById(String id) {
//        UserDTO user = userMapper.findById(id);
//        if (user == null) {
//            log.info(Constants.SERVICE_LOG_TAG + " no find user by id, id = {}", id);
//        }
//        return user;
//    }
//
//    @Override
//    public PagedList<UserDTO> findLikeByMobile(String mobile, UserType type, int page, int size) {
//        PageInfo pageInfo = new PageInfo(page, size);
//        int count = userMapper.countLikeByMobile(mobile, type);
//        List<UserDTO> users = userMapper.findLikeByMobile(mobile, type, pageInfo.getOffset(), pageInfo.getSize());
//        return new PagedList<>(users, count);
//    }
//
//    @Override
//    public void insert(UserDTO user) throws BizException {
//        if (StringUtils.isBlank(user.getId())) {
//            user.setId(StringUtil.uuid());
//        }
//        if (StringUtils.isEmpty(user.getPassword())) {
//            user.setPassword("123456");
//        }
//        String pwd = validateUtil.SHAException(user.getPassword());
//        user.setPassword(pwd);
//        userMapper.insert(user);
//    }
//
//    @Override
//    public void update(UserDTO user) throws BizException {
//        validateUtil.paramNotNull(user.getId(), user.getMobile());
//        UserDTO before = userMapper.findById(user.getId());
//        validateUtil.ObjectNotNull(before, UserDTO.class, GlobalErrorCode.USER_NOT_EXIST);
//        UserType type = (null == user.getType()) ? UserType.E : user.getType();
//        UserDTO byMobile = userMapper.findByMobile(user.getMobile(), type);
//        if ((byMobile != null) && !byMobile.getId().equals(user.getId())) {
//            throw new BizException(UserErrorCode.USER_MOBILE_REPEAT);
//        }
//        userMapper.update(user);
//    }
//
//    @Override
//    @Transactional(rollbackFor = {BizException.class, Throwable.class})
//    public void resetPwd(String id) throws BizException {
//
//        //1.生成新密码；
//        UserDTO user = userMapper.findById(id);
//        validateUtil.ObjectNotNull(user, UserDTO.class, GlobalErrorCode.USER_NOT_EXIST);
//        String resetPwd = StringUtil.getString(FinalParams.MIN, FinalParams.MAX, FinalParams.LENGTH);
//        //2.修改密码；
//        String password = validateUtil.SHAException(resetPwd);
//        userMapper.resetPwd(id, password);
//
//        //3.存储修改密码的最后更新时间
//        Long date = new Date().getTime();
//        jedisUtil.set(ComFinalParams.UPDATE_PASSWORD_TAG + id, date.toString());
//
//        //4.把新密码发给用户
//        JsonResult jsonResult = messageServiceClient.sendSms(FinalParams.TEMPLATE, resetPwd, user.getMobile(), Realm.EMPLOYEE);
//        validateUtil.getJsonData(jsonResult, null, "messageService-sendSms");
//    }
//
//    @Override
//    public List<UserDTO> findByMobiles(List<String> mobiles) {
//        return userMapper.findByMobiles(mobiles);
//    }
//
//    @Override
//    public PagedList<UserDTO> findByCondition(String organizationId, String positionId, String name, String mobile, Integer page, Integer size) {
//        PageInfo pageInfo = new PageInfo(page, size);
//        Integer totalSize = userMapper.countByCondition(organizationId, positionId, name, mobile);
//        List<UserDTO> users = userMapper.findPageByCondition(organizationId, positionId, name, mobile,
//                pageInfo.getOffset(), pageInfo.getSize());
//        return new PagedList<>(users, totalSize);
//    }
//
//    @Override
//    public void updateKujiale(String id, boolean status) {
//        userMapper.updateKujiale(id, status);
//    }
//
    /**
     * 加密异常处理
     */
    private String SHAException(String password) throws BizException {
        try {
            password = StringUtil.getSHA(password);
            return password;
        } catch (NoSuchAlgorithmException e) {
            log.error(Constants.SERVICE_LOG_TAG + " 未找到加密方法, \n {}", e);
            throw new BizException(GlobalErrorCode.UNKNOWN);
        } catch (UnsupportedEncodingException e) {
            log.error(Constants.SERVICE_LOG_TAG + " 格式转换错误, \n {}", e);
            throw new BizException(GlobalErrorCode.UNKNOWN);
        }
    }

    /**
     * 补全用户登录信息
     */
    private UserDTO getPrivilegeAndRole(UserDTO user, UserLoginLog userLoginLog, TerminalType terminal,
                                        MenuScope scope, UserType type) throws BizException {
        String userId = user.getId();
        //查询添加用户权限
        List<String> privileges = privilegeMapper.findByUser(userId, scope);
        switch (user.getType()) {
            case E:
                //用户是员工获取员名字
                EmployeeDTO employeeDTO = employeeMapper.findById(userId, null, true);
                user.setName(employeeDTO.getName());
                user.setCompanyCode(employeeDTO.getCompanyCode());
                //获取用户职位
                List<Position> positions = this.getPosition(user, userLoginLog, terminal, user.getType());
                user.setPositions(positions);
                break;
            case V:
                EmployeeDTO guardian = employeeMapper.findById(user.getId(), null, true);
                if (null != guardian) {
                    user.setCompanyCode(guardian.getCompanyCode());
                }
                user.setEmployee(null);
                break;
            case A:
                break;
            default:
                log.error(Constants.SERVICE_LOG_TAG + " 用户类型不存在, user = {}", user);
                this.dealLoginFollow(user.getMobile(), userLoginLog, FinalParams.LOGIN_FAIL, terminal, type);
                throw new BizException(PrivilegeErrorCode.USER_TYPE_IS_NULL);
        }
        user.setPrivileges(privileges);

        Map<String, List<String>> map = new HashMap<>();
        if (scope == null) {
            for (MenuScope menuScope : MenuScope.values()) {
                List<String> scopePrivilege = privilegeMapper.findByUser(userId, menuScope);
                map.put(menuScope.getCode(), scopePrivilege);
            }
        }
        user.setMenuPrivilege(map);
        setDataScope(user);
        Long time = System.currentTimeMillis();
//        jedisUtil.set(ComFinalParams.AUTHORIZE_TAG + userId, time.toString());
        user.setNewPrivilegeTime(time);
        return user;
    }

    private void setDataScope(UserDTO user) {
        try {
            EmployeeDTO employee = employeeMapper.findById(user.getId(), null, true);
            PositionPower priviliges = validateUtil.getPriviliges(employee);
//            log.info("login setDataScope:{}", JsonUtil.toJsonString(priviliges));
            user.setPositionPower(priviliges);
        } catch (Exception e) {
            log.error("setDataScope error: {}", e);
        }
    }

    //根据用户的id获取用户的职位
    private List<Position> getPosition(UserDTO user, UserLoginLog userLoginLog, TerminalType terminal, UserType type) throws BizException {
        //查询添加用户职位
        List<Position> positions = positionMapper.findByEmployee(user.getId(), false);

        if (positions == null || positions.isEmpty()) {
            if (userLoginLog != null) {
                this.dealLoginFollow(user.getMobile(), userLoginLog, FinalParams.LOGIN_FAIL, terminal, type);
            }
            throw new BizException(PrivilegeErrorCode.EMP_NO_POSITION);
        }
        //查询员工的业务权限
        for (Position position : positions) {
            List<DictPrivilege> dictPriByPosition = positionMapper.findDictPriByPosition(position.getPositionId());
            Map<String, List<String>> map = new HashMap<>();
            for (DictPrivilege dict : dictPriByPosition) {
                if (!map.containsKey(dict.getDictType().getCode())) {
                    map.put(dict.getDictType().getCode(), new ArrayList<>());
                }
                List<String> list = map.get(dict.getDictType().getCode());
                list.add(dict.getDictValue());
                map.put(dict.getDictType().getCode(), list);
            }
            position.setBizPrivileges(map);

//            position.setTags(contentMapper.listTagOfRealm(com.example.platform.pojo.Realm.POSITION, position.getPositionId()));
        }
        return positions;
    }

    /**
     * 登录后续不影响主流程的处理
     */
    private void dealLoginFollow(String mobile, UserLoginLog userLoginLog, String message, TerminalType terminal, UserType type) {
        try {
            ((UserServiceImpl) AopContext.currentProxy()).loginFollow(mobile, userLoginLog, message, terminal, type);
        } catch (Throwable e) {
            log.warn(Constants.SERVICE_LOG_TAG + "login follow fail \n {}", e);
        }
    }

    /**
     * 登录操作的后续步骤（修改最后登录时间；写登录日志）
     * 由于这些不影响登录的主流程，所以新开事物进行控制
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void loginFollow(String mobile, UserLoginLog userLoginLog, String message, TerminalType terminal, UserType type) {
        String logs = "user(" + type.getMessage() + ")：" + userLoginLog.getMobile() + " login " + message;
        userLoginLog.setLog(logs);
        if (message.equals(FinalParams.LOGIN_SUCCESS)) {
            userLoginLog.setSuccess(true);
        } else {
            userLoginLog.setSuccess(false);
        }
        userMapper.updateLastLoginTime(mobile, type);
        if (StringUtils.isNotBlank(userLoginLog.getId()) && !userLoginLog.getId().startsWith("10.72.")) {
            userLoginLogMapper.insert(userLoginLog);
        }
        //记录登陆的操作终端
        OperateTerminal operateTerminal = new OperateTerminal(StringUtil.uuid(), com.example.platform.pojo.enums.Realm.LOGIN,
                null, mobile, terminal);
        terminalMapper.insertOperateTerminal(operateTerminal);
    }
//
//    /**
//     * 创建员工账号
//     *
//     * @param employeeId 为了保证员工id和账号id一致
//     * @param mobile
//     * @param password
//     * @throws BizException
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void createEmp(String employeeId, String mobile, String password) throws BizException {
//        UserDTO userDTO = userMapper.findById(employeeId);
//        if (userDTO != null) {
//            //老员工再入职,启用账号
//            String pwd = validateUtil.SHAException(password);
//            userDTO.setMobile(mobile);
//            userDTO.setType(UserType.E);
//            userMapper.update(userDTO);
//            userMapper.updateStatus(userDTO.getId(), true);
//            userMapper.resetPwd(userDTO.getId(), pwd);
//            return;
//        }
//        log.info("onBoarding addUser, employeeId:{}, mobile:{}", employeeId, mobile);
//        UserDTO user = new UserDTO();
//        user.setId(employeeId);
//        user.setMobile(mobile);
//        user.setType(UserType.E);
//        String pwd = validateUtil.SHAException(password);
//        user.setPassword(pwd);
//        try {
//            userMapper.insert(user);
//        } catch (DuplicateKeyException key) {
//            throw new BizException(EmployeeFileErrorCode.USER_SAVE_ERROR, "手机号已存在:" + mobile);
//        } catch (Exception e) {
//            log.error("onBoarding addUser error :{}", e.getMessage());
//            throw new BizException(EmployeeFileErrorCode.USER_SAVE_ERROR);
//        }
//    }
//
//    @Override
//    public String genPassword(String idCard) {
//        if (StringUtils.isEmpty(idCard)) {
//            return null;
//        }
//        return idCard.substring(idCard.length() - 6);
//    }
//
//
//    @Override
//    public List<EmpFileOperateLogVO> getOperationLog(String yearMonth) throws BizException {
//        List<String> scenes = Arrays.asList(UserOperateLogHandler.DISABLE_OR_ENABLE_USER, EmpManageOperateLogHandler.EMP_MANAGE_TRANSFER_JOB);
//        return employeeFileLogMapper.getOperationLog(null, scenes,
//                ReqHolder.getLoginUserCompanyCode(), yearMonth);
//    }
//
//    @Override
//    public Set<String> switchCompany(String companyCode, UserDTO employee) {
//        log.info("员工：{}，id：{},切换公司至：{}", employee.getName(), employee.getId(), companyCode);
//        Set<String> dealerCodes = dealerOrgUtil.switchCompany(employee.getId(), companyCode);
//        if(!CollectionUtils.isEmpty(dealerCodes) && dealerCodes.size() > 1){
//            return new TreeSet<>(dealerCodes);
//        }
//        return dealerCodes;
//    }
//
//    @Override
//    public GzhUser findUserByOpenidAndScope(String openid, int subscribe, String scope) {
//        String gzhId = gzhAppConfig.getGzhAppId(scope);
//        GzhUser wxUser = mpUserMapper.findUserByOpenid(openid, subscribe, gzhId);
//        return wxUser;
//    }
//
//    @Override
//    public GzhLoginUser getGzhLoginUserInfo(String gzhCode, String scope) throws BizException {
//
//        log.info("进入免验证码接口！");
//        String gzhId = gzhAppConfig.getGzhAppId(scope);
//        GzhAccessToken gzhAccessToken = accessTokenMapper.getAccessTokenByAppid(gzhId);
//
//        String url = ProjectConst.GET_WEBAUTH_URL
//                .replace("APPID", gzhId)
//                .replace("SECRET", gzhAccessToken.getAppsecret())
//                .replace("CODE", gzhCode);
//        String result1 = WeiXinUtils.doGet(url);
//
//        com.alibaba.fastjson.JSONObject jsonObject = JSONObject.parseObject(result1);
//        String errcode = jsonObject.getString("errcode");
//        if (org.apache.commons.lang.StringUtils.isNotBlank(errcode)) {
//            String errmsg = jsonObject.getString("errmsg");
//            log.error(errcode);
//            throw new BizException(errcode, errmsg);
//        }
//        String openid = jsonObject.getString("openid");
//        GzhUser user = mpUserMapper.findUserByOpenid(openid, -1, gzhId);
//        if (null == user) {
//            throw new BizException("-1", "没查询到该微信用户,请重新关注公众号！");
//        }
//        MpUserDsp mpUserDsp = mpUserMapper.queryMpUserDsp(openid, gzhId, scope);
//        GzhLoginUser gzhLoginUser = new GzhLoginUser();
//        gzhLoginUser.setGzhUser(user);
//        gzhLoginUser.setMpUserDsp(mpUserDsp);
//        return gzhLoginUser;
//    }

}
