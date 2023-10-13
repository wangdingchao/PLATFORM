package com.example.platform.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.platform.pojo.Administrator;
import com.example.platform.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministratorMapper extends BaseMapper<Administrator> {

    // 查询用户详情
    // Query administrator's details
    Administrator findDesc(Integer aid);

    // 删除用户的所有角色
    // Delete all administrator's roles
    void deleteAdminAllRoles(Integer aid);

    // 给用户添加角色 需要添加两个参数: 一个是用户id aid 和 角色id rid
    // Adding a role to a user, you need to add two parameters: aid and rid.
    void addAdminRoles(@Param("aid") Integer aid,
                       @Param("rid") Integer rid);

    // 根据用户名查询权限
    // Query permissions by username
    List<Permission> findAllPermission(String username);
}
