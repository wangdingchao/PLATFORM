package com.example.platform.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Game event attribute
 * 游戏赛事基本信息
 */
@Data
public class GameEvent {
//    @TableId
    private Integer gid; // gameEvent id
    private String title; // Title for the game
    private Date signUpTime; // Sign up time;
    private Date gameStartTime; // game start time
    private String roadMap; // game road map for the game
    private String rule; // game rule

}
