package com.example.platform.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Description: Permission in the database
 */
@Data
public class Permission {

    @TableId
    private Integer pid; // Permission's pid in system, Unique and not empty(Primary Key)
    private String permissionName; // Permission name
    private String permissionDesc;// Permission details

}
