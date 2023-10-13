package com.example.platform.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * Game event attribute
 * 游戏赛事基本信息
 */
@Data
public class GameEvent {
    @TableId
    private Integer geID; // gameEvent id
    private String title; // Title for the game
    private Date signUp; // Sign up time;
    private Date gameStart; // game start time
    private String roadMap; // game road map for the game
    private String rule; // game rule

}
