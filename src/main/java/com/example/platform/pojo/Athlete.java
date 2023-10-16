package com.example.platform.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description Athletic attributes
 *
 */
@Data
public class Athlete {

//    @TableId
    private Integer aid; // Athlete's pid in system, Unique and not empty(Primary Key)
    private int age; // Athlete's age
    private String sex; // Athlete's gender
    private String username; // Athlete's username in the system
    private String userPassword; // Athlete's password in the system
    private String phoneNumber; // Athlete's phone number
    private String idNumber; // Athlete's id number of the Country
    private String address; // Athlete's address
    private String email; // Athlete's email address
    private String info; // Extra information that athlete provides.
    private Date registrationTime; // Athlete registration time
    private Date updateTime; // Athlete update Time;

//    @TableId
    private MarathonCategory marathonCategory; // Athletes sign up for different types of marathons

}
