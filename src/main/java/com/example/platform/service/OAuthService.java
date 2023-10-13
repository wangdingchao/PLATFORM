package com.example.platform.service;



import com.example.platform.exception.BizException;
import com.example.platform.pojo.LoginReqVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AuthService
 *
 * @author hyl
 * @since 2022/9/8 14:20
 **/
public interface OAuthService {


    /**
     * 手机号+密码登录
     * @param vo       参数
     * @param request
     * @param response
     * @return 结果
     */
    Object loginByPassword(LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException;

//    /**
//     * 手机号免密登录
//     * @param vo       参数
//     * @param request
//     * @param response
//     * @return 结果
//     */
//    Object loginByMobile(LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException;
//
//    /**
//     * 获取详情
//     * @param vo
//     * @param request
//     * @param response
//     * @return
//     */
//    Object myself(String vo, HttpServletRequest request, HttpServletResponse response);
//
//    /**
//     * 退出登录
//     * @param token
//     * @param request
//     * @param response
//     * @return
//     */
//    Object logout(String token, HttpServletRequest request, HttpServletResponse response);
//
//    /**
//     * 切换另一个手机号码登录（供超级管理员使用）
//     * @param vo
//     * @param currToken
//     * @param request
//     * @param response
//     * @return
//     */
//    Object mock(LoginReqVo vo, String currToken, HttpServletRequest request, HttpServletResponse response) throws BizException;
//
//    /**
//     * 微信小程序获取token的接口
//     * @param vo
//     * @param request
//     * @param response
//     * @return
//     */
//    Object getTokenByCode(LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException;
//
//    /**
//     * 验证码登录
//     * @param vo 请求参数
//     * @param request 请求
//     * @param response 响应
//     * @return 结果
//     * @throws BizException 业务异常
//     */
//    Object loginByVerificationCode(LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException;
//
//    /**
//     * 切换公司
//     * @param companyCode 公司code
//     * @param dealerCodes 经销商
//     * @param request 请求体
//     * @return 结果
//     * @throws BizException 切换公司异常
//     */
//    Object switchCompany(String companyCode, HttpServletRequest request) throws BizException;

}
