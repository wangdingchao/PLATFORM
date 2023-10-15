package com.example.platform.pojo.log;

import com.example.platform.pojo.Realm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作人
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {
    //操作人身份
    private Realm realm;
    //用户标识
    private String id;
    //姓名
    private String name;
    //组织机构标识
    private String orgId;
    //职位标识
    private String posstionId;


}
