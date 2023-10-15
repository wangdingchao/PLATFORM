package com.example.platform.controller.req;

import com.example.platform.error.GlobalErrorCode;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.*;
import com.example.platform.pojo.constants.ComFinalParams;
import com.example.platform.pojo.log.Operator;
import com.example.platform.utils.JedisUtil;
import com.example.platform.utils.JsonUtil;
import com.example.platform.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author liujiaming
 */
@Component
@Slf4j
public class ReqInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws BizException {
        ReqInfo reqInfo = new ReqInfo();
        Map<String, String[]> parameters = request.getParameterMap();
        log.info("dealer req headers:{}, params: {}", JsonUtil.toJsonString(getHeadersInfo(request)), JsonUtil.toJsonString(parameters));
        EmployeeDTO employee = null;
        try {
            employee = getEmployee(request);
        } catch (Exception e) {
            String token = request.getParameter("token");
            log.error("获取登录人失败, token :{} error :{}", token, e.getMessage());
        }
        if(employee == null && (request.getRequestURI().contains("/callback") ||
                                request.getRequestURI().contains("/notify") ||
                                request.getRequestURI().contains("/addTrainingExperience") ||
                                request.getRequestURI().contains("/bi/fine") ||
                                request.getRequestURI().contains("/nc/fine") ||
                                request.getRequestURI().contains("/recruiters/archiveStaging") ||
                                request.getRequestURI().contains("/recruiters/onBoardingRegistration/submit") ||
                                request.getRequestURI().contains("/upload/")
                                )
        ) {
            //这两个类型的接口无需登录
            employee = new EmployeeDTO();
            employee.setId("SYSTEM");
        }
        reqInfo.loginUser = employee;
        reqInfo.linkId = UUID.randomUUID().toString();;
        ReqHolder.setReqInfo(reqInfo);
        return true;
    }

    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
        ReqHolder.remove();
    }


    @Autowired
    private JedisUtil jedisUtil;


    public EmployeeDTO getEmployee(HttpServletRequest request) throws BizException {
        EmployeeDTO employee = new EmployeeDTO();
        UserDTO user = this.getUser(request);
        Operator operator = this.getOperator(request);
        if (operator != null && user != null && user.getType().equals(UserType.E)) {
            employee.setId(user.getId());
            employee.setName(user.getName());
            employee.setMobile(user.getMobile());
            employee.setOrgId(operator.getOrgId());
            employee.setPositionId(operator.getPosstionId());
            List<Position> positions = user.getPositions();
            Position position = (Position) ListUtil.find(positions, (i) -> {
                return i.getPositionId().equals(employee.getPositionId()) && i.getOrgId().equals(employee.getOrgId());
            });
            if (position == null) {
                throw new BizException(GlobalErrorCode.USER_NOT_EXIST, new String[0]);
            } else {
                employee.setPosition(new Position(position.getPositionName()));
                Organization organization = new Organization();
                organization.setName(position.getOrgName());
                String strJson = this.jedisUtil.get("DEALER:ORG:" + employee.getOrgId());

                employee.setOrganization(organization);
                return employee;
            }
        } else {
            throw new BizException(GlobalErrorCode.USER_NOT_PRIVILEGE, new String[0]);
        }
    }

    public Operator getOperator(HttpServletRequest request) {
        String org = request.getHeader("org");
        String position = request.getHeader("position");
        UserDTO user = this.getUser(request);
        return user != null && org != null && position != null ? new Operator(Realm.EMPLOYEE, user.getId(), user.getName(), org, position) : null;
    }

    protected UserDTO getUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie[] var4 = cookies;
                int var5 = cookies.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Cookie cookie = var4[var6];
                    if ("token".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }

                if (StringUtils.isBlank(token)) {
                    token = request.getParameter("token");
                }
            }
        }

        token = ComFinalParams.TOKEN_TAG + token;
        String json = this.jedisUtil.get(token);
        Map map = (Map) JsonUtil.fromJson(json, HashMap.class);
        if (map == null) {
            return null;
        } else {
            map.remove("primaryPosition");
            map.remove("$ttl");
            map.remove("lastLoginTime");
            UserDTO user = (UserDTO)JsonUtil.fromJson(JsonUtil.beanToJson(map), UserDTO.class);
            return user;
        }
    }
}
