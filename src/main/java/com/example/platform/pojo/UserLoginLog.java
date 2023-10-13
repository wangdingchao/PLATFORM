package com.example.platform.pojo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.platform.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-1-25
 * 描述：用户登录日志表的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginLog {

    @Length(max = 36)
    private String id;                            //日志id

    @Length(max = 13)
    private String mobile;                      //登录名(手机号)

    @Length(max = 100)
    private String ip;                           //登录ip

    @Length(max = 1000)
    private String log;                          //日志信息

    @Length(max = 200)
    private String agent;                        //user-agent

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;                     //登录时间

    @NotNull
    private Boolean Success;                    //登录成功与否

    public UserLoginLog(HttpServletRequest request, String mobile) {
        //新建登录日志，补全登录日志信息
        this.agent = request.getHeader("user-agent");
        this.ip = RequestUtil.getIP(request);
        if (StringUtils.isNotBlank(this.ip)) {
            this.ip = this.ip.replace("10.72.1.60", "").replace("10.72.1.61", "").replace("10.72.1.39", "").
                    replace(" ", "").replace(",", "");
        }
        this.id = StringUtil.uuid();
        this.mobile = mobile;
    }
}
