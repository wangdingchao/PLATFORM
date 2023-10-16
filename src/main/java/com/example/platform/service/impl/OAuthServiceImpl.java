package com.example.platform.service.impl;

import com.example.platform.error.GlobalErrorCode;
import com.example.platform.error.OAuthErrorCode;
import com.example.platform.exception.BizException;
import com.example.platform.mapper.AuthMapper;
import com.example.platform.pojo.*;
import com.example.platform.pojo.enums.AppIdEnum;
import com.example.platform.pojo.enums.MenuScope;
import com.example.platform.pojo.enums.TerminalType;
import com.example.platform.service.IUserService;
import com.example.platform.service.OAuthService;
import com.example.platform.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * AuthServiceImpl
 *
 * @author hyl
 * @since 2023/04/14 15:10
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {

//    private final JedisUtil jedisUtil;
    private final IUserService userService;
//    private final IRoleService roleService;
//    private final OfficialAccountService officialAccountService;
//    private final PermissionService permissionService;
//
//    private final OrganizationMapper organizationMapper;
//
//    private final DealerOrgUtil dealerOrgUtil;

//    @Value("${oauth.reuseToken}")
//    private boolean reuseToken;
//    @Value("${oauth.ttl}")
//    private int ttl;
//    @Value("${ids.login.url}")
//    private String url;
//    @Value("${ids.login.key}")
//    private String key;

    /**
     * WEB页面密码登录 手机号+密码登录
     *
     * @param vo       参数
     * @param request  请求体
     * @param response 返回体
     * @return 结果
     */
    @Override
    public Object loginByPassword(LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException {
        AppSource appSource;

        // 验证 appId 和 appSecret
        appSource = parseAppSource(vo);

        String scope = appSource.getScope();
        String source = appSource.getSource();
        LoginUser loginUser = null;

        MobilePasswordReqDto loginBody = new MobilePasswordReqDto();
        loginBody.setScope(appSource.getScope());
        loginBody.setSource(appSource.getSource());
        loginBody.setMobile(vo.getMobile());
        loginBody.setPassword(vo.getPassword());
        loginBody.setSource(source);

        UserLoginLog userLoginLog = new UserLoginLog(request, loginBody.getMobile());
        TerminalType terminal = RequestUtil.getTerminal(request);
        UserDTO payload;

        if (StringUtils.isNotBlank(vo.getMobile()) && StringUtils.isNotBlank(vo.getPassword())) {
            if (StringUtils.isNotEmpty(vo.getWechatOpenId())) {
                loginBody.setImageUrl(Optional.ofNullable(vo.getImageUrl()).orElse(""));
                loginBody.setWechatOpenId(vo.getWechatOpenId());
                loginBody.setUnionId(vo.getUnionId());
            }
//            if (AppScopeEnum.DSP.name().equals(scope) || AppScopeEnum.SCM.name().equals(scope) || AppScopeEnum.PROJ.name().equals(scope)) {
//                appSource.setScope("");
//            }
            //  任务  ，完成 数据库表的设计， 参考role 实体类， 实现对用户手机号，密码的校验  （ 密码需要加密，可以自己在网上找加密算法）
            payload = userService.login(loginBody.getMobile(), loginBody.getPassword(), UserType.E, userLoginLog, terminal, MenuScope.getEnum(""));


//            log.info("payload：{}", JSONUtil.toJsonStr(payload));
        } else {
            throw new BizException(OAuthErrorCode.LOGIN_EXCEPTION_UNKNOWN_MODE);
        }

        // 组装用户数据
//        this.assembleUserData(payload);

        if (loginUser != null){
            List<Role> roles = payload.getRole();
            SysUser sysUser = loginUser.getSysUser();
            List<SysRole> roleList = new ArrayList<>();
            Set<String> roleSet = new HashSet<>();
            for (Role role : roles) {
                roleSet.add(role.getRoleKey());
                SysRole sysRole = new SysRole();
                sysRole.setRoleName(role.getName());
                sysRole.setRoleKey(role.getRoleKey());
                sysRole.setStatus("0");
                sysRole.setFlag(true);
                roleList.add(sysRole);
            }
            sysUser.setRoles(roleList);
            Set<String> permissions = new HashSet<>(payload.getPrivileges());
            loginUser.setPermissions(permissions);
            loginUser.setRoles(roleSet);
        }
        payload.setIdsUser(loginUser);
        // 生成token
//        return genToken(payload, appSource, payload.getId());
        return null;
    }


    /**
     * 手机号免密登录
     *
     * @param vo       参数
     * @param request  请求体
     * @param response 返回体
     * @return 结果
     */
//    @Override
//    public Object loginByMobile(LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException {
//        AppSource appSource;
//        appSource = parseAppSource(vo);
//
//        String scope = appSource.getScope();
//        String source = appSource.getSource();
//        LoginUser loginUser = null;
//        if (CharSequenceUtil.isNotBlank(vo.getMobile())) {
//            // 获取IDS用户信息
//            String resStr = this.getIDSLoginUser(vo.getMobile());
//            if (resStr != null) {
//                JSONObject parse = JSONUtil.parseObj(resStr);
//                int code = parse.getByPath("code", int.class);
//                Object data = parse.getByPath("data");
//                String msg = parse.getByPath("msg").toString();
//
//                if (code != 200 && (AppScopeEnum.SCM.name().equals(scope) || AppScopeEnum.PROJ.name().equals(scope))) {
//                    throw new BizException(GlobalErrorCode.IDS_SERVICE_ERROR, msg);
//                }
//                if (data != null) {
//                    loginUser = parse.getByPath("data", LoginUser.class);
//                }
//                if (code == 200 && (AppScopeEnum.SCM.name().equals(scope) || AppScopeEnum.PROJ.name().equals(scope))) {
//                    if (loginUser != null && loginUser.getId() == null) {
//                        UserDTO payload = new UserDTO();
//                        payload.setIdsUser(loginUser);
//                        return genToken(payload, appSource, loginUser.getUserid().toString());
//                    }
//                }
//            }
//        }
//
//        MobilePasswordReqDto loginBody = BeanUtil.copyProperties(appSource, MobilePasswordReqDto.class);
//        loginBody.setMobile(vo.getMobile());
//        loginBody.setPassword(vo.getPassword());
//        loginBody.setSource(source);
//
//        UserLoginLog userLoginLog = new UserLoginLog(request, loginBody.getMobile());
//        TerminalType terminal = RequestUtil.getTerminal(request);
//        UserDTO user = new UserDTO();
//        if (AppScopeEnum.MIS.name().equals(scope)) {
//            //内部系统SSO，免密码
//            if (AppSourceEnum.WEB.name().equals(source)) {
//                user = userService.loginByMobile(vo.getMobile(), userLoginLog, terminal, null, loginBody.getImageUrl(), loginBody.getWechatOpenId(), loginBody.getUnionId(), UserType.E);
//            }
//        } else if (Lists.newArrayList(AppScopeEnum.CRM.name(), AppScopeEnum.INSTALL.name(), AppScopeEnum.DELIVERY.name(), AppScopeEnum.WECOM.name()).contains(scope) && vo.getPassword() == null) {
//            // 内部小程序登录：索小秘、美家匠心、美家速达
//            if (AppSourceEnum.MINAPP.name().equals(appSource.getSource()) || AppSourceEnum.QW.name().equals(appSource.getSource())) {
//                if (CharSequenceUtil.isNotEmpty(vo.getWechatOpenId())) {
//                    loginBody.setImageUrl(Optional.ofNullable(vo.getImageUrl()).orElse(""));
//                    loginBody.setWechatOpenId(vo.getWechatOpenId());
//                    loginBody.setUnionId(Optional.ofNullable(vo.getUnionId()).orElse(""));
//                }
//                user = userService.loginByMobile(vo.getMobile(), userLoginLog, terminal, MenuScope.getEnum(""), loginBody.getImageUrl(), loginBody.getWechatOpenId(), loginBody.getUnionId(), UserType.E);
//
//            }
//        } else {
//            throw new BizException(OAuthErrorCode.LOGIN_EXCEPTION_UNKNOWN_MODE);
//        }
//
//        UserDTO payload = BeanUtil.copyProperties(user, UserDTO.class);
//        // 组装用户数据
//        this.assembleUserData(payload);
//
//        if (loginUser != null){
//            List<Role> roles = payload.getRole();
//            SysUser sysUser = loginUser.getSysUser();
//            List<SysRole> roleList = new ArrayList<>();
//            Set<String> roleSet = new HashSet<>();
//            for (Role role : roles) {
//                roleSet.add(role.getRoleKey());
//                SysRole sysRole = new SysRole();
//                sysRole.setRoleName(role.getName());
//                sysRole.setRoleKey(role.getRoleKey());
//                sysRole.setStatus("0");
//                sysRole.setFlag(true);
//                roleList.add(sysRole);
//            }
//            sysUser.setRoles(roleList);
//            Set<String> permissions = new HashSet<>(payload.getPrivileges());
//            loginUser.setPermissions(permissions);
//            loginUser.setRoles(roleSet);
//        }
//        payload.setIdsUser(loginUser);
//        // 生成token
//        return genToken(payload, appSource, user.getId());
//    }
//
//
//    /**
//     * 获取详情
//     *
//     * @param token    令牌
//     * @param request  请求
//     * @param response 返回
//     * @return 结果
//     */
//    @Override
//    public Object myself(String token, HttpServletRequest request, HttpServletResponse response) {
//        UserDTO payload = getJsonSync("TOKEN", token);
//        Map<String, Object> resultMap = new HashMap<>();
//        if (payload != null) {
//            resultMap.put("success", true);
//            resultMap.put("data", payload);
//        } else {
//            resultMap.put("success", false);
//            resultMap.put("data", null);
//        }
//        return resultMap;
//    }
//
//    @Override
//    public Object logout(String token, HttpServletRequest request, HttpServletResponse response) {
//        jedisUtil.del(ComFinalParams.TOKEN_TAG + token);
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("success", true);
//        return resultMap;
//    }
//
//    @Override
//    public Object mock(LoginReqVo vo, String currToken, HttpServletRequest request, HttpServletResponse response) throws BizException {
//        // 取得当前登录用户
//        UserDTO currUser = this.getJsonSync("TOKEN", currToken);
//        if (currUser == null || currUser.getMobile() == null || currUser.getName() == null) {
//            response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
//            Map<String, Object> resultMap = new HashMap<>();
//            resultMap.put("success", false);
//            resultMap.put("error", "非法请求，无法获得当前登录用户 token：" + currToken);
//            return resultMap;
//        }
//
//        UserDTO mockBy = currUser.getMockBy();
//        if (mockBy != null) {
//            currUser = mockBy;
//        }
//
//        // 验证 appId 和 appSecret
//        AppSource appSource;
//        try {
//            appSource = parseAppSource(vo);
//        } catch (Exception e) {
//            response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
//            Map<String, Object> resultMap = new HashMap<>();
//            resultMap.put("success", false);
//            resultMap.put("error", e.getMessage());
//            return resultMap;
//        }
//
//        // 用新身份切换登录
//        String currMobile = vo.getMobile();
//        UserLoginLog userLoginLog = new UserLoginLog(request, currMobile);
//        TerminalType terminal = RequestUtil.getTerminal(request);
//        UserDTO user = userService.loginByMobile(currMobile, userLoginLog, terminal, null, null, null, null, UserType.E);
//
//        // 如果存在切换数据，清除已有的经销商编码。
//        String companyCode = jedisUtil.get(ComFinalParams.COMPANY_SWITCH + user.getId());
//        if (StringUtils.isNotBlank(companyCode)) {
//            // 设置切换公司后的公司编码。
//            List<DealerBrandLabel> dealerBrandLabels = organizationMapper.findDealerBrandLabel(companyCode);
//            Set<String> dealerCodes = dealerBrandLabels.stream().map(DealerBrandLabel::getDealerCode).collect(Collectors.toSet());
//            user.setCompanyCode(companyCode);
//            user.setDealerCodes(dealerCodes);
//
//            EmployeeDTO employee = new EmployeeDTO();
//            employee.setId(user.getId());
//            employee.setCompanyCode(companyCode);
//            EmployeeDTO employeeDTO = dealerOrgUtil.listPageCompanyCodesAndDealerCodes(employee);
//            user.setInnerPageCompanyCodes(new LinkedHashSet<>(employeeDTO.getInnerPageCompanyCodes()));
//            user.setInnerPageDealerCodes(new LinkedHashSet<>(employeeDTO.getInnerPageDealerCodes()));
//        }
//
//        UserDTO payload = BeanUtil.copyProperties(user, UserDTO.class);
//
//        // 获取IDS用户信息
//        LoginUser loginUser = null;
//        String resStr = this.getIDSLoginUser(payload.getMobile());
//        if (resStr != null) {
//            JSONObject parse = JSONUtil.parseObj(resStr);
//            int code = parse.getByPath("code", int.class);
//            Object data = parse.getByPath("data");
//            if (code == 200 && data != null) {
//                loginUser = parse.getByPath("data", LoginUser.class);
//                payload.setIdsUser(loginUser);
//            }
//        }
//
//
//        // 登录成功，生成token
//        if (payload.getPositions() != null && !payload.getPositions().isEmpty() && ObjectUtil.isNotEmpty(payload.getPositions().get(0))) {
//            payload.setPrimaryPosition(payload.getPositions().get(0));
//        } else {
//            payload.setPrimaryPosition(null);
//        }
//
//        payload.setMockBy(currUser.getMobile().equals(payload.getMobile()) ? null : currUser);
//        String oldToken;
//        String token;
//
//        if (payload.getMockBy() != null) {
//            oldToken = jedisUtil.get(ComFinalParams.TOKEN_TAG + ("DSP".equals(appSource.getScope()) ? "" : (appSource.getScope() + ":")) + appSource.getSource() + ":MOCK:" + payload.getMockBy().getId() + ":USER" + ":" + payload.getId());
//            if (oldToken != null) {
//                token = oldToken;
//            } else {
//                //原账号已过期，重新生成TOKEN
//                token = UUID.randomUUID().toString();
//            }
//
//            // 切换登录，有效期30分钟
//            payload.setTtl(30 * 60);
//            this.cacheTokenIndex(ComFinalParams.TOKEN_TAG + ("DSP".equals(appSource.getScope()) ? "" : (appSource.getScope() + ":")) + appSource.getSource() + ":MOCK:" + payload.getMockBy().getId() + ":USER", payload.getId(), token, null);
//            log.warn("{}{} 切换到 {}{}的账号", payload.getMockBy().getName(), payload.getMockBy().getMobile(), payload.getName(), payload.getMobile());
//        } else {
//            // 切换回原账号
//            oldToken = jedisUtil.get(ComFinalParams.TOKEN_TAG + ("DSP".equals(appSource.getScope()) ? "" : (appSource.getScope() + ":")) + appSource.getSource() + ":USER" + ":" + payload.getId());
//            payload.setTtl(this.ttl);
//            if (oldToken != null) {
//                token = oldToken;
//            } else {
//                //原账号已过期，重新生成TOKEN
//                token = UUID.randomUUID().toString();
//            }
//        }
//
//
//        List<Role> roleBeanList = roleService.findByUserId(user.getId());
//
//        List<Role> roleList1 = new ArrayList<>();
//        roleBeanList.forEach(x -> {
//            Role roleResVo = new Role();
//            roleResVo.setId(x.getId());
//            roleResVo.setName(x.getName());
//            roleResVo.setDesc(x.getDesc());
//            roleResVo.setRoleKey(x.getRoleKey());
//            roleList1.add(roleResVo);
//        });
//        payload.setRole(roleList1);
//
//        if (loginUser != null){
//            List<Role> roles = payload.getRole();
//            SysUser sysUser = loginUser.getSysUser();
//            List<SysRole> roleList = new ArrayList<>();
//            Set<String> roleSet = new HashSet<>();
//            for (Role role : roles) {
//                roleSet.add(role.getRoleKey());
//                SysRole sysRole = new SysRole();
//                sysRole.setRoleName(role.getName());
//                sysRole.setRoleKey(role.getRoleKey());
//                sysRole.setStatus("0");
//                sysRole.setFlag(true);
//                roleList.add(sysRole);
//            }
//            sysUser.setRoles(roleList);
//            Set<String> permissions = new HashSet<>(payload.getPrivileges());
//            loginUser.setPermissions(permissions);
//            loginUser.setRoles(roleSet);
//        }
//        payload.setIdsUser(loginUser);
//
//        this.cacheToken("TOKEN", token, JSONUtil.parseObj(payload), payload.getTtl());
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("success", true);
//        resultMap.put("token", token);
//        resultMap.put("maxAge", payload.getTtl());
//        resultMap.put("user", payload);
//        resultMap.put("appId", appSource.getAppId());
//        resultMap.put("scope", appSource.getScope());
//        resultMap.put("source", appSource.getSource());
//        return resultMap;
//    }
//
//    @Override
//    public Object getTokenByCode(LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException {
//        AppSource appSource;
//        try {
//            appSource = parseAppSource(vo);
//        } catch (Exception e) {
//            response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
//            Map<String, Object> resultMap = new HashMap<>();
//            resultMap.put("success", false);
//            resultMap.put("error", e.getMessage());
//            return resultMap;
//        }
//
//
//        if (vo.getCode() == null) {
//            response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
//            Map<String, Object> resultMap = new HashMap<>();
//            resultMap.put("success", false);
//            resultMap.put("error", "code不能为空");
//            return resultMap;
//        }
//
//
//        Map<String, Object> map = userService.getToken(vo.getCode(), vo.getImageUrl(), MenuScope.getEnum(appSource.getScope()), appSource.getSource(), request);
//
//        Object userId = map.get("userId");
//        if (userId != null) {
//
//            // 获取token
//            String token = this.getOneSync(ComFinalParams.TOKEN_TAG + ("DSP".equals(appSource.getScope()) ? "" : (appSource.getScope() + ":")) + appSource.getSource() + ":USER", (String) userId);
//
//            if (token == null) {
//                //没有token，需要登录
//                Map<String, Object> resultMap = new HashMap<>();
//                Map<String, Object> data = new HashMap<>();
//                resultMap.put("success", true);
//                data.put("isToken", false);
//                data.put("openId", map.get("openId"));
//                data.put("unionId", map.get("unionId"));
//                data.put("phone", map.get("phone"));
//                resultMap.put("data", data);
//                return resultMap;
//            }
//
//            UserDTO payload = this.getJsonSync("TOKEN", token);
//            if (payload == null) {
//                //有token，没有payload，需要重新登录
//                Map<String, Object> resultMap = new HashMap<>();
//                Map<String, Object> data = new HashMap<>();
//                resultMap.put("success", true);
//                data.put("isToken", false);
//                data.put("openId", map.get("openId"));
//                data.put("unionId", map.get("unionId"));
//                data.put("phone", map.get("phone"));
//                resultMap.put("data", data);
//                return resultMap;
//            }
//
//
//            // 10、COOPERATION("合作方带单")
//            // 11、CFHL("厨房护理服务商") 单独刷新权限
//            if ("CFHL".equals(appSource.getScope())) {
//
//                Map map2 = userService.findPrivilege(userId.toString(), MenuScope.getEnum(appSource.getScope()));
//                // 刷新权限
//                permissionService.refreshPermission(userId.toString(),MenuScope.getEnum(appSource.getScope()),token, map2, request, response);
//
//                Map<String, Object> resultMap = new HashMap<>();
//                Map<String, Object> data = new HashMap<>();
//                resultMap.put("success", true);
//                data.put("token", token);
//                data.put("isToken", true);
//                data.put("data", payload);
//                data.put("phone", map.get("phone"));
//                resultMap.put("data", data);
//                return resultMap;
//
//
//
//                //刷新权限 (企业微信不走验权)
//            } else if ("GZH".equals(appSource.getSource()) || "QW".equals(appSource.getSource())) {
//                Map<String, Object> resultMap = new HashMap<>();
//                Map<String, Object> data = new HashMap<>();
//                resultMap.put("success", true);
//                data.put("token", token);
//                data.put("isToken", true);
//                data.put("data", payload);
//                data.put("phone", map.get("phone"));
//                resultMap.put("data", data);
//                return resultMap;
//            } else {
//                // 验证权限范围
//                if (Boolean.FALSE.equals(this.hasScopePrivilege(payload, appSource))) {
//                    // 没有小程序权限
//                    response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
//                    Map<String, Object> resultMap = new HashMap<>();
//                    resultMap.put("success", false);
//                    resultMap.put("error", "没有使用权限");
//                    return resultMap;
//                } else {
//                    Object unionId = map.get("unionId");
//                    Object wechatOpenId = map.get("wechatOpenId");
//                    payload.setUnionId((String) unionId);
//                    payload.setWechatOpenId((String) wechatOpenId);
//                    Map<String, Object> resultMap = new HashMap<>();
//                    Map<String, Object> data = new HashMap<>();
//                    resultMap.put("success", true);
//                    data.put("isToken", true);
//                    data.put("token", token);
//                    data.put("data", payload);
//                    resultMap.put("data", data);
//                    return resultMap;
//                }
//
//            }
//
//
//        } else {
//
//            Map<String, Object> resultMap = new HashMap<>();
//            Map<String, Object> data = new HashMap<>();
//            resultMap.put("success", true);
//            data.put("isToken", false);
//            data.put("openId", map.get("openId"));
//            data.put("unionId", map.get("unionId"));
//            resultMap.put("data", data);
//            return resultMap;
//
//        }
//
//    }
//
//    /**
//     * 验证码登录
//     *
//     * @param vo       请求参数
//     * @param request  请求
//     * @param response 响应
//     * @return 结果
//     * @throws BizException 业务异常
//     */
//    @Override
//    public Object loginByVerificationCode(LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException {
//
//        AppSource appSource;
//        appSource = parseAppSource(vo);
//
//        String scope = appSource.getScope();
//        String source = appSource.getSource();
//        LoginUser loginUser = null;
//        if (CharSequenceUtil.isNotBlank(vo.getMobile())) {
//            // 获取IDS用户信息
//            String resStr = this.getIDSLoginUser(vo.getMobile());
//            if (resStr != null) {
//                JSONObject parse = JSONUtil.parseObj(resStr);
//                int code = parse.getByPath("code", int.class);
//                Object data = parse.getByPath("data");
//                String msg = parse.getByPath("msg").toString();
//
//                if (code != 200 && (AppScopeEnum.SCM.name().equals(scope) || AppScopeEnum.PROJ.name().equals(scope))) {
//                    throw new BizException(GlobalErrorCode.IDS_SERVICE_ERROR, msg);
//                }
//                if (data != null) {
//                    loginUser = parse.getByPath("data", LoginUser.class);
//                }
//                if (code == 200 && (AppScopeEnum.SCM.name().equals(scope) || AppScopeEnum.PROJ.name().equals(scope))) {
//                    if (loginUser != null && loginUser.getId() == null) {
//                        UserDTO payload = new UserDTO();
//                        payload.setIdsUser(loginUser);
//                        return genToken(payload, appSource, loginUser.getUserid().toString());
//                    }
//                }
//            }
//        }
//
//
//        MobilePasswordReqDto loginBody = BeanUtil.copyProperties(appSource, MobilePasswordReqDto.class);
//        loginBody.setMobile(vo.getMobile());
//        loginBody.setPassword(vo.getPassword());
//        loginBody.setSource(source);
//
//
//        UserDTO payload;
//        if (AppSourceEnum.GZH.name().equals(appSource.getSource())) {
//            // 公众号
//            log.info("公众号用户手机号验证码绑定登录->请求参数 {},{},{},{}", vo.getMobile(), vo.getCode(), vo.getWechatOpenId(), appSource.getScope());
//            Object newData = officialAccountService.officialAccountLoginByCaptcha(vo.getMobile(), vo.getCode(), vo.getWechatOpenId(), appSource.getScope(), request);
//            log.info("公众号用户手机号验证码绑定登录->响应数据 {}", JSONUtil.toJsonStr(newData));
//            Assert.notNull(newData, () -> new BizException(OAuthErrorCode.BINDING_ERROR));
//            payload = BeanUtil.toBean(newData, UserDTO.class);
//
//        } else {
//            throw new BizException(OAuthErrorCode.LOGIN_EXCEPTION_UNKNOWN_MODE);
//        }
//
//        // 组装用户数据
//        this.assembleUserData(payload);
//
//
//        if (loginUser != null){
//            List<Role> roles = payload.getRole();
//            SysUser sysUser = loginUser.getSysUser();
//            List<SysRole> roleList = new ArrayList<>();
//            Set<String> roleSet = new HashSet<>();
//            for (Role role : roles) {
//                roleSet.add(role.getRoleKey());
//                SysRole sysRole = new SysRole();
//                sysRole.setRoleName(role.getName());
//                sysRole.setRoleKey(role.getRoleKey());
//                sysRole.setStatus("0");
//                sysRole.setFlag(true);
//                roleList.add(sysRole);
//            }
//            sysUser.setRoles(roleList);
//            Set<String> permissions = new HashSet<>(payload.getPrivileges());
//            loginUser.setPermissions(permissions);
//            loginUser.setRoles(roleSet);
//        }
//        payload.setIdsUser(loginUser);
//        // 生成token
//        return genToken(payload, appSource, payload.getId());
//    }
//
//    /**
//     * 切换公司
//     *
//     * @param companyCode 公司code
//     * @param request     请求体
//     * @return 结果
//     * @throws BizException 切换公司异常
//     */
//    @Override
//    public Object switchCompany(String companyCode, HttpServletRequest request) throws BizException {
//
//        String token = TokenUtil.getToken("token", request);
//        UserDTO oldPayLoad = this.getJsonSync("TOKEN", token);
//        Set<String> dealerCodes = userService.switchCompany(companyCode, oldPayLoad);
//        oldPayLoad.setCompanyCode(companyCode);
//        oldPayLoad.setDealerCodes(dealerCodes);
//
//        EmployeeDTO employee = new EmployeeDTO();
//        employee.setId(oldPayLoad.getId());
//        employee.setCompanyCode(companyCode);
//        EmployeeDTO employeeDTO = dealerOrgUtil.listPageCompanyCodesAndDealerCodes(employee);
//        oldPayLoad.setInnerPageCompanyCodes(new LinkedHashSet<>(employeeDTO.getInnerPageCompanyCodes()));
//        oldPayLoad.setInnerPageDealerCodes(new LinkedHashSet<>(employeeDTO.getInnerPageDealerCodes()));
//
//        if (ObjectUtil.isNotEmpty(oldPayLoad) && oldPayLoad.getTtl() != null) {
//            jedisUtil.set("TOKEN:" + token, JSONUtil.toJsonStr(oldPayLoad), oldPayLoad.getTtl());
//        } else {
//            throw new BizException(OAuthErrorCode.SWITCH_COMPANY_FAIL);
//        }
//        Map<String, Object> res = new HashMap<>();
//        res.put("success", true);
//        res.put("dealerCodes", oldPayLoad.getDealerCodes());
//        return res;
//    }
//
//
//    /**
//     * 验证 appId 和 appSecret
//     *
//     * @param vo 请求参数
//     * @return 结果
//     */
    private AppSource parseAppSource(LoginReqVo vo) throws BizException {
        String appId = vo.getAppId();
        String appSecret = vo.getAppSecret();

        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(appSecret)) {
            throw new BizException(GlobalErrorCode.PARAM_VALID_ERROR, "Bad request, appId and appSecret are required");
        }

        String[] split = appId.split("-");
        if (split.length != 3 || StringUtils.isEmpty(split[0]) || StringUtils.isEmpty(split[1]) || StringUtils.isEmpty(split[2])) {
            throw new BizException(GlobalErrorCode.PARAM_VALID_ERROR, "Unauthorized request, invalid appId");
        }

        String valueByKey = AppIdEnum.getValueByKey(appId);
        if (valueByKey == null || !valueByKey.equals(appSecret)) {
            throw new BizException(GlobalErrorCode.PARAM_VALID_ERROR, "Unauthorized request, invalid appSecret");
        }

        AppSource appSource = new AppSource();
        appSource.setAppId(appId);
        appSource.setScope(split[0]);
        appSource.setEnv(split[1]);
        appSource.setSource(split[2]);
        return appSource;
    }
//
//
//    /**
//     * 组装用户数据
//     *
//     * @param payload 参数
//     */
//    private void assembleUserData(UserDTO payload) {
//
//        if (payload.getPositions() != null && !payload.getPositions().isEmpty() && ObjectUtil.isNotEmpty(payload.getPositions().get(0))) {
//            payload.setPrimaryPosition(payload.getPositions().get(0));
//        }
//
//
//        List<Role> roles = roleService.findByUserId(payload.getId());
//
//        List<Role> role = new ArrayList<>();
//        roles.forEach(x -> {
//            Role roleResVo = new Role();
//            roleResVo.setId(x.getId());
//            roleResVo.setName(x.getName());
//            roleResVo.setDesc(x.getDesc());
//            roleResVo.setRoleKey(x.getRoleKey());
//            role.add(roleResVo);
//        });
//        payload.setRole(role);
//    }
//
//    /**
//     * 权限验证
//     *
//     * @param payload   用户
//     * @param appSource 来源
//     * @return 结果
//     */
//    private Boolean hasScopePrivilege(UserDTO payload, AppSource appSource) {
//
//        if (ObjectUtil.isEmpty(appSource) || ObjectUtil.isEmpty(payload) || payload.getPrivileges() == null || payload.getPrivileges().isEmpty()) {
//            return false;
//        }
//
//        if (AppSourceEnum.MINAPP.name().equals(appSource.getSource())) {
//            return payload.getPrivileges().contains(appSource.getScope() + "-" + appSource.getSource());
//        }
//
//        return true;
//    }
//
//
//    /**
//     * 获取token
//     *
//     * @param payload
//     * @param appSource
//     * @param userId
//     * @return
//     */
//    private Object genToken(UserDTO payload, AppSource appSource, String userId) {
//
//
////        if (payload.getIdsUser() != null) {
////            if (payload.getIdsUser().getPrivileges() == null){
////                payload.getIdsUser().setPrivileges(new HashSet<>());
////            }
////            List<String> privileges = payload.getPrivileges();
////            if (privileges != null){
////                payload.getIdsUser().getPrivileges().addAll(privileges);
////            }
////        }
//
//        StringBuilder stringBuilder = new StringBuilder(ComFinalParams.TOKEN_TAG);
//        if (!AppScopeEnum.DSP.name().equals(appSource.getScope()) && !AppScopeEnum.SCM.name().equals(appSource.getScope())) {
//            stringBuilder.append(appSource.getScope()).append(":");
//        }
//        stringBuilder.append(appSource.getSource()).append(":USER");
//        String oldToken = jedisUtil.get(stringBuilder + ":" + userId);
//        // 赋值最新权限时间
//        payload.setNewPrivilegeTime(new Date().getTime());
//
//        // 移动端登录，token永久有效； 其它来源，默认1*24小时有效
//        Integer localTtl = null;
//        if (!AppSourceEnum.MINAPP.name().equals(appSource.getSource()) && !AppSourceEnum.H5.name().equals(appSource.getSource()) && !AppSourceEnum.QW.name().equals(appSource.getSource())) {
//            localTtl = this.ttl;
//        }
//        Map<String, Object> res = new HashMap<>();
//        if (oldToken != null && reuseToken) {
//            // 已登录，且允许多次登录，则继续使用其TOKEN，刷新其PAYLOAD和TTL
//            cacheToken("TOKEN", oldToken, JSONUtil.parseObj(payload), localTtl);
//            res.put("token", oldToken);
//        } else {
//            // 已登录，不允许多处登录，则作废旧TOKEN，重新生成TOKEN
//            if (oldToken != null) {
//                jedisUtil.del(ComFinalParams.TOKEN_TAG + oldToken);
//            }
//            // 未登录，则登录后重新生成TOKEN。
//            String newToken = UUID.randomUUID().toString();
//            this.cacheToken("TOKEN", newToken, JSONUtil.parseObj(payload), localTtl);
//            this.cacheTokenIndex(stringBuilder.toString(), userId, newToken, null);
//            res.put("token", newToken);
//        }
//        res.put("success", true);
//        res.put("maxAge", localTtl);
//        res.put("user", payload);
//        res.put("appId", appSource.getAppId());
//        res.put("scope", appSource.getScope());
//        res.put("source", appSource.getSource());
//        return res;
//    }
//
//    public static void main(String[] args) {
//        HashSet<String> objects = new HashSet<>();
//        List<String> privileges = null;
//        objects.addAll(privileges);
//    }
//
//
//    /**
//     * 缓存token
//     *
//     * @param namespace
//     * @param key
//     * @param value
//     * @param ttl
//     */
//    public void cacheToken(String namespace, String key, JSONObject value, Integer ttl) {
//        if (ttl == null) {
//            jedisUtil.set(namespace + ":" + key, JSONUtil.toJsonStr(value));
//        } else {
//            if (value != null) {
//                value.putByPath("$ttl", ttl);
//            }
//            jedisUtil.set(namespace + ":" + key, JSONUtil.toJsonStr(value), ttl);
//        }
//    }
//
//    /**
//     * 缓存token索引
//     *
//     * @param namespace
//     * @param key
//     * @param value
//     * @param ttl
//     */
//    private void cacheTokenIndex(String namespace, String key, String value, Integer ttl) {
//        if (ttl == null) {
//            jedisUtil.set(namespace + ":" + key, JSONUtil.toJsonStr(value));
//        } else {
//            jedisUtil.set(namespace + ":" + key, JSONUtil.toJsonStr(value), ttl);
//        }
//    }
//
//
//    public UserDTO getJsonSync(String beforeFlag, String token) {
//        String str = this.getOneSync(beforeFlag, token);
//        if (str != null) {
//            return JSONUtil.toBean(str, UserDTO.class);
//        }
//        return null;
//    }
//
//
//    private String getOneSync(String namespace, String key) {
//        return jedisUtil.get(namespace + ":" + key);
//    }
//
//
//    /**
//     * IDS 密码登录
//     *
//     * @param mobile
//     * @param password
//     * @return
//     */
//    private String getIDSLoginUser(String mobile, String password) {
//        // 获取ids 用户信息
//        log.info("==========================获取ids 用户信息============================");
//        try {
//            Map<String, Object> queryMap = new HashMap<>();
//            queryMap.put("type", "pwd");
//            queryMap.put("key", key);
//            queryMap.put("password", password);
//            queryMap.put("username", mobile);
//            log.info("ids请求参数为{}", JSONUtil.toJsonStr(queryMap));
//            String resStr = HttpUtil.post(url, queryMap, 5000);
//            log.info("ids响应参数为{}", resStr);
//            if (resStr != null) {
//                log.info("请求 loginUser成功");
//                return resStr;
//            }
//        } catch (Exception e) {
//            log.error("获取ids LoginUser失败:{}", e.getMessage(), e);
//            return null;
//        }
//        return null;
//    }
//
//    /**
//     * IDS免密登录
//     *
//     * @param mobile
//     * @return
//     */
//    private String getIDSLoginUser(String mobile) {
//        // 获取ids 用户信息
//        log.info("==========================获取ids 用户信息============================");
//        try {
//            Map<String, Object> queryMap = new HashMap<>();
//            queryMap.put("type", "noPwd");
//            queryMap.put("key", key);
//            queryMap.put("username", mobile);
//            log.info("ids请求参数为{}", JSONUtil.toJsonStr(queryMap));
//            String resStr = HttpUtil.post(url, queryMap, 5000);
//            log.info("ids响应参数为{}", resStr);
//            if (resStr != null) {
//                log.info("请求 loginUser成功");
//                return resStr;
//            }
//        } catch (Exception e) {
//            log.error("获取ids LoginUser失败:{}", e.getMessage(), e);
//            return null;
//        }
//        return null;
//    }


}

