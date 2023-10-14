package com.example.platform.service;

import com.example.platform.exception.BizException;
import com.example.platform.pojo.EmployeeDTO;
import com.example.platform.pojo.UserDTO;
import com.example.platform.pojo.UserLoginLog;
import com.example.platform.pojo.UserType;
import com.example.platform.pojo.enums.MenuScope;
import com.example.platform.pojo.enums.TerminalType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-1-22
 * 描述：user表service层的接口
 */
public interface IUserService {
    /**
     * 通过密码登录
     * @param mobile
     *      手机号
     * @param password
     *      密码
     * @param type
     *      用户类型
     * @param terminal
     *      终端
     * @param scope
     *      作用域
     */
    UserDTO login(String mobile, String password, UserType type, UserLoginLog userLoginLog, TerminalType terminal,
                  MenuScope scope) throws BizException;

    /**
     * 登出
     * @param idsaccessToken ids token
     */
    void idsLogout(String idsaccessToken);

//    /**
//     * 根据用手机号和密码修改，用户密码
//     * @param mobile
//     *      手机号
//     * @param oldPass
//     *      原密码
//     * @param newPass
//     * @param type
//     */
//    void updatePassword(String mobile, String oldPass, String newPass, UserType type) throws BizException;
//
//    /**
//     * 验证码登录接口
//     * @param mobile
//     *      手机号
//     * @param code
//     *      验证码
//     * @param imageUrl
//     *      头像的url
//     * @param wechatOpenId
//     *      微信openId
//     * @param type
//     *      用户类型
//     * @param userLoginLog
//     *      登录日志
//     * @param terminal
//     *      终端
//     * @param scope
//     *      作用域
//     */
//    UserDTO loginByMobile(String mobile, String code, String imageUrl, String wechatOpenId, UserType type,
//                          UserLoginLog userLoginLog, TerminalType terminal, MenuScope scope) throws BizException;
//
//    /**
//     * 手机号登陆接口
//     * @param mobile
//     *      手机号
//     * @param terminal
//     *      终端
//     * @param scope
//     *      作用域
//     * @param imageUrl
//     *      头像url
//     * @param wechatOpenId
//     *      微信openId
//     * @param type
//     *      用户类型
//     */
//    UserDTO loginByMobile(String mobile, UserLoginLog userLoginLog, TerminalType terminal, MenuScope scope,
//                          String imageUrl, String wechatOpenId, String unionId, UserType type) throws BizException;
//
//    /**
//     * 重置密码
//     * @param mobile
//     *      手机号
//     * @param onePass
//     *      第一次密码
//     * @param twoPass
//     *      第二次密码
//     * @param code
//     *      验证码
//     * @param type
//     *      用户类型
//     */
//    void resetPassword(String mobile, String onePass, String twoPass, String code, UserType type)throws BizException;
//
    /**
     * 登录的后续操作
     * @param mobile
     *      用户的手机号
     * @param userLoginLog
     *      登录日志
     * @param message
     * @param type
     */
    void loginFollow(String mobile, UserLoginLog userLoginLog, String message, TerminalType terminal, UserType type) throws BizException;
//
//    /**
//     * 根据code获取token, 存在返回token;不存在返回openId
//     * @param code
//     *      临时code
//     * @param imageUrl
//     *      头像
//     */
//    Map<String,Object> getToken(String code, String imageUrl, MenuScope scope,HttpServletRequest request) throws BizException;
//
//    Map<String, Object> getToken(String code, String imageUrl, MenuScope scope, String source, HttpServletRequest request) throws BizException;

    /**
     * 根据code获取openId和解密数据
     * @param code
     *      临时code
     * @param decryptData
     *      解密数据
     * @param vi
     *      加密算法初始量
     */
//    Map<String, String> getDecrypt(String code, String decryptData, String vi, MenuScope scope) throws BizException;

    /**
     * 保存用户的推送权限formId
     * @param formId
     *      推送权限
     */
//    void saveFormId(String formId, EmployeeDTO employee);

    /**
     * 根据用户Id查询用户功能权限和业务权限
     * @param userId
     *      用户id
     * @param scope
     *      作用域
     */
//    Map<String,Object> findPrivilege(String userId, MenuScope scope) throws BizException;

    /**
     * 根据用户的id修改用户的状态
     * @param id
     *      用户id
     * @param status
     *      用户状态
     */
//    void updateStatus(String id, boolean status);

    /**
     * 根据用户id查询用户
     * @param id
     *      用户id
     */
//    UserDTO findById(String id);

    /**
     *根据用户手机号模糊分页查询用户
     * @param mobile
     *      用户手机号
     * @param type
     *      用户类型
     */
//    PagedList<UserDTO> findLikeByMobile(String mobile, UserType type, int page, int size);

    /**
     * 新增用户
     * @param user
     *      用户信息的封装de对象
     */
    void  insert(UserDTO user) throws BizException;

    /**
     * 修改用户信息
     * @param user
     *   修改信息的封装对象
     */
//    void  update(UserDTO user) throws BizException;

    /**
     * 重置密码
     * @param id
     *   用户的id
     */
//    void resetPwd(String id) throws BizException;

    /**
     * 根据用户手机号列表，查询用户列表
     * @param mobiles
     *      用户手机号集合
     */
    List<UserDTO> findByMobiles(List<String> mobiles);

    /**
     * 根据条件查询用户信息
     * @param organizationId
     *      组织机构id
     * @param positionId
     *      职位id
     * @param name
     *      员工信息
     * @param mobile
     *      用户手机号
     * @param page
     *      页码
     * @param size
     *      数量
     */
//    PagedList<UserDTO> findByCondition(String organizationId, String positionId, String name, String mobile,
//                                       Integer page, Integer size);

    /**
     * 根据用户的id修改是否开通酷家乐的状态
     * @param id
     *      用户id
     * @param status
     *      酷家乐状态
     */
//    void updateKujiale(String id, boolean status);


    //入职添加用户
//    void createEmp(String employeeId, String mobile, String password) throws BizException;

//    String genPassword(String idCard);

//    List<EmpFileOperateLogVO> getOperationLog(String yearMonth) throws BizException;

    /**
     * 切换公司
     * @param companyCode 公司编码
     * @param employee 员工信息
     */
//    Set<String> switchCompany(String companyCode, UserDTO employee);

//    GzhUser findUserByOpenidAndScope(String openid, int subscribe, String scope);
//
//    GzhLoginUser getGzhLoginUserInfo(String gzhCode, String scope) throws BizException;
}
