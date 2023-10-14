package com.example.platform.mapper;

import com.example.platform.pojo.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-1-25
 * 描述：用户登录日志表dao层的接口
 */
@Mapper
public interface UserLoginLogMapper {

    void insert(UserLoginLog userLoginLog);

    int countByKeyWord(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                       @Param("success") Boolean success, @Param("mobile") String mobile);

    List<UserLoginLog> findByKeyWord(@Param("offset") int offset, @Param("size") int size,
                                     @Param("startTime") Date startTime, @Param("endTime") Date endTime,
                                     @Param("success") Boolean success, @Param("mobile") String mobile);
}
