package com.example.platform.controller;

import com.example.platform.dto.JsonResult;
import com.example.platform.error.OAuthErrorCode;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.LoginReqVo;
import com.example.platform.pojo.enums.MenuScope;
import com.example.platform.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * AuthController
 *
 * @author hyl
 * @since 2022/9/8 14:21
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService authService;
//    private final IUserService userService;
//    private final IMessageService messageService;


    /**
     * 手机号+密码登录
     *
     * @return java.lang.Object
     * @author hyl
     * @since 2022/9/8 14:22
     **/
    @PostMapping ("/mobile/password")
    public Object loginByPassword(@RequestBody LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException {
        if (vo.getAppId() == null){
            String appId = request.getParameter("appId");
            vo.setAppId(appId);
        }
        if (vo.getAppSecret() == null){
            String appSecret = request.getParameter("appSecret");
            vo.setAppSecret(appSecret);
        }
//        log.info("手机号+密码登录 -> [{}]", JSONUtil.toJsonStr(vo));
        if (StringUtils.isBlank(vo.getMobile())){
            return JsonResult.fail(OAuthErrorCode.THE_ACCOUNT_CANNOT_BE_EMPTY.getCode(),OAuthErrorCode.THE_ACCOUNT_CANNOT_BE_EMPTY.getMessage());
        }
        if (StringUtils.isBlank(vo.getPassword())){
            return JsonResult.fail(OAuthErrorCode.THE_PASSWORD_CANNOT_BE_EMPTY.getCode(),OAuthErrorCode.THE_PASSWORD_CANNOT_BE_EMPTY.getMessage());
        }
        return authService.loginByPassword(vo,request,response);
    }


    /**
     * 手机号免密登录
     *
     * @return java.lang.Object
     * @author hyl
     * @since 2022/9/8 14:22
     **/
    @PostMapping ("/mobile/nopwd")
    public Object loginByMobile(@RequestBody LoginReqVo vo, HttpServletRequest request, HttpServletResponse response) throws BizException {
        if (vo.getAppId() == null){
            String appId = request.getParameter("appId");
            vo.setAppId(appId);
        }
        if (vo.getAppSecret() == null){
            String appSecret = request.getParameter("appSecret");
            vo.setAppSecret(appSecret);
        }
        if (StringUtils.isBlank(vo.getMobile())){
            return JsonResult.fail(OAuthErrorCode.THE_ACCOUNT_CANNOT_BE_EMPTY.getCode(),OAuthErrorCode.THE_ACCOUNT_CANNOT_BE_EMPTY.getMessage());
        }
//        if(CharSequenceUtil.isBlank(vo.getWechatOpenId())){
//            return JsonResult.fail(OAuthErrorCode.OPEN_ID_MISSING.getCode(),OAuthErrorCode.OPEN_ID_MISSING.getMessage());
//        }
//        log.info("手机号免密登录 -> [{}]", JSONUtil.toJsonStr(vo));
//        return authService.loginByMobile(vo,request,response);
        return null;
    }


    /**
     * 验证码登录
     *
     * @return java.lang.Object
     * @author hyl
     * @since 2022/9/8 14:22
     **/
    @PostMapping ("/mobile/captcha")
    public Object loginByVerificationCode(@RequestBody LoginReqVo vo,HttpServletRequest request,HttpServletResponse response) throws BizException {
        if (vo.getAppId() == null){
            String appId = request.getParameter("appId");
            vo.setAppId(appId);
        }
        if (vo.getAppSecret() == null){
            String appSecret = request.getParameter("appSecret");
            vo.setAppSecret(appSecret);
        }
        if (StringUtils.isBlank(vo.getMobile())){
            return JsonResult.fail(OAuthErrorCode.THE_ACCOUNT_CANNOT_BE_EMPTY.getCode(),OAuthErrorCode.THE_ACCOUNT_CANNOT_BE_EMPTY.getMessage());
        }
//        log.info("验证码登录 -> [{}]", JSONUtil.toJsonStr(vo));
//        return authService.loginByVerificationCode(vo,request,response);
        return null;
    }


    /**
     * 微信小程序获取token的接口
     *
     * @return java.lang.Object
     * @author hyl
     * @since 2022/9/8 14:22
     **/
    @RequestMapping ("/tecent/code/{code}")
    @ResponseBody
    public Object getTokenByCode(@PathVariable("code") String code,LoginReqVo vo,HttpServletRequest request,HttpServletResponse response) throws BizException {
        vo.setCode(code);
//        log.info("微信小程序登录 -> [{}]", JSONUtil.toJsonStr(vo));
//        return authService.getTokenByCode(vo,request,response);
        return null;
    }


    /**
     * 退出登录
     *
     * @return java.lang.Object
     * @author hyl
     * @since 2022/9/8 14:22
     **/
    @PostMapping ("/logout")
    public Object logout(HttpServletRequest request,HttpServletResponse response) {
//        String token = TokenUtil.getToken("token",request);
//        if (token == null){
//            return JsonResult.fail(OAuthErrorCode.TOKEN_MISSING.getCode(),OAuthErrorCode.TOKEN_MISSING.getMessage());
//        }
//        log.info("退出登录 -> [{}]", token);
//        return authService.logout(token,request,response);
        return null;
    }


    /**
     * 发送手机验证码
     * @param mobile 手机号
     * @param scope 作用域
     * @return
     * @throws BizException
     */
    @PutMapping("/mobile/send/captcha")
//    public JsonResult sendCaptchaByMobileAndScope(@RequestParam("mobile") String mobile,@RequestParam("scope") GzhScope scope) throws BizException {
//        Boolean successed = messageService.sendCaptchaByMobileAndScope(mobile, scope);
//        return JsonResult.success(successed);
//    }


    /**
     * 根据code获取openId和解密数据（获取用户手机号授权解密）
     * @param code 临时code
     * @param decryptData 解密数据
     * @param vi 加密算法初始量
     * @param scope 作用域
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/wx/data/decrypt", method = RequestMethod.GET)
    public JsonResult getDecrypt(@RequestParam("code") String code,
                                 @RequestParam("decryptData") String decryptData,
                                 @RequestParam("vi") String vi,
                                 @RequestParam(value = "scope", required = false) MenuScope scope) throws BizException {
        // scope为空，默认索小密
        if (null == scope){
            scope = MenuScope.CRM;
        }
//        Map<String, String> map = userService.getDecrypt(code, decryptData, vi, scope);
        return JsonResult.success(new HashMap<>());
    }


}
