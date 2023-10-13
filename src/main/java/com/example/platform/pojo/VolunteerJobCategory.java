package com.example.platform.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Description: Job categories for volunteer
 */
@Data
public class VolunteerJobCategory {
    @TableId
    private Integer vjcId; // vjcid in system, Unique and not empty(Primary Key)
    private String vjcName; // vjc name,
    private String jobDuties; // Job duties
}
