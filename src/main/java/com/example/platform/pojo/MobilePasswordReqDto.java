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
public class MobilePasswordReqDto {

    private String scope;

    private String source;

    private String mobile;

    private String password;

    private String wechatOpenId;

    private String imageUrl;

    private String unionId;

    private UserType type;


}
