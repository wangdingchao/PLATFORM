package com.example.platform.pojo;

import com.example.platform.pojo.enums.MenuScope;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiniAppUser {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 小程序openid
     */
    private String openId;

    /**
     * 小程序unionId
     */
    private String unionId;

    /**
     * 头像
     */
    private String imageUrl;

    /**
     * 作用域
     */
    private MenuScope scope;

}
