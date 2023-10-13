package com.example.platform.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * @description Role in the system
 */
@Data
public class Role {

    @TableId
    private Integer rid; // Role's rid in system, Unique and not empty(Primary Key)
    private String roleName; // Role name
    private String roleDesc; // Description of the Role

    @TableField(exist = false) // Not the field in the database
    private List<Permission> permissions;// Permission set

}
