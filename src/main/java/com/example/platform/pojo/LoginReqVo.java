package com.example.platform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqVo {

    private String code;

    private String appId;

    private String appSecret;

    private String mobile;

    private String password;

    private String wechatOpenId;

    private String imageUrl;

    private String unionId;

    private String type;

}
