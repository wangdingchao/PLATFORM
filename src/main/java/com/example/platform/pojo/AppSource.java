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
public class AppSource {

    private String appId;

    private String scope;

    private String env;

    private String source;


}
