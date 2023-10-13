package com.example.platform.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * @Description Administrator attributes
 *
 */
@Data
public class Administrator {
    @TableId
    private Integer aid; // Administrator's pid in system, Unique and not empty(Primary Key)
    private String username; // Administrator's username in system
    private String password; //
    private String phoneNum;
    private String email;
    private boolean status; // Status, true: available, false: forbidden
    @TableField(exist = false) // Not a field in the database
    private List<Role> roles; // Role set
}
