package com.example.platform.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Shuai Fang
 * @version 1.0
 * @description 信息上报
 * @date 2023/10/12 16:59
 */
@Data
public class InformationReporting {

    @TableId
    private Integer irId; // Information Reporting ID, primary key.
    private String imgAddress; // the address of the image.
    private String remark; // remark of the information.

}
