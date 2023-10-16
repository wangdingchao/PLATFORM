package com.example.platform.pojo;

import lombok.Data;

/**
 * @Description: Volunteer attributes
 */
@Data
public class Volunteer {
//    @TableId
    private Integer vid;
    private int age;
    private int sex;
    private String email;
    private String phoneNumber;
    private String idNumber;
    private VolunteerJobCategory volunteerJobCategory;
}

