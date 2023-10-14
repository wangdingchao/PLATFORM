package com.example.platform.mapper;

import com.example.platform.pojo.UserDTO;
import com.example.platform.pojo.UserType;
//import com.sogal.oauth.domain.MiniAppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-1-25
 * 描述：user表dao层的接口
 */
@Mapper
public interface UserMapper {

    UserDTO login(@Param("mobile") String mobile, @Param("password") String password, @Param("type") UserType type);

    void updatePassword(@Param("mobile") String mobile, @Param("type") UserType type, @Param("newPass") String newPass);

    void updateOpenId(@Param("id") String id,
                      @Param("imageUrl") String imageUrl,
                      @Param("openId") String wechatOpenId);

    void updateLastLoginTime(@Param("mobile") String mobile, @Param("type") UserType type);

    void updateStatus(@Param("id") String id, @Param("status") boolean status);

    void updateKujiale(@Param("id") String id, @Param("status") boolean status);

    void resetPwd(@Param("id")String id,@Param("pwd") String pwd);

    void updateImgByOpenId(@Param("imageUrl") String imageUrl, @Param("openId") String openId);

    void updateUnionId(@Param("unionId") String unionId, @Param("openId") String openId);

    void  update(UserDTO user);

    UserDTO findByMobile(@Param("mobile") String mobile, @Param("type") UserType type);

    List<UserDTO> findByMobiles(@Param("mobiles") List<String> mobiles);

    List<UserDTO> findLikeByMobile(@Param("mobile") String mobile, @Param("type") UserType type,
                                   @Param("offset") int offset, @Param("size") int size);

    List<UserDTO> findPageByCondition(@Param("organizationId") String organizationId,
                                      @Param("positionId") String positionId,
                                      @Param("name") String name,
                                      @Param("mobile") String mobile,
                                      @Param("offset") int offset,
                                      @Param("size") int size);

    int countByCondition(@Param("organizationId") String organizationId,
                         @Param("positionId") String positionId,
                         @Param("name") String name,
                         @Param("mobile") String mobile);

    int countLikeByMobile(@Param("mobile") String mobile, @Param("type") UserType type);

    UserDTO findById(@Param("id") String id);

    UserDTO findByOpenId(@Param("openId") String openId, @Param("type") UserType type);

    String findNameById(@Param("id") String id);

    List<String> findIdByRole(@Param("roleId") String roleId);

    void  insert(UserDTO user);

//    void insertMiniAppUser(MiniAppUser miniAppUser);
//
//    MiniAppUser findMiniAppUserByOpenId(@Param("openId") String openId);
}
