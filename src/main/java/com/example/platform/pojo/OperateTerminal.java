package com.example.platform.pojo;

import com.example.platform.pojo.enums.TerminalType;
import com.example.platform.utils.StringUtil;
import com.example.platform.pojo.enums.Realm;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-10-25
 * 描述：操作终端实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperateTerminal {

    //id
    private String id;

    //操作类型
    private Realm realm;

    //客户id
    private String customerId;

    //实体类id
    private String entity;

    //终端类型
    private TerminalType terminalType;

    //操作时间
    private Date operateTime;

    public OperateTerminal(String id, Realm realm, String customerId, String entity, TerminalType terminalType) {
        this.id = id;
        this.realm = realm;
        this.customerId = customerId;
        this.entity = entity;
        this.terminalType = terminalType;
    }
    public OperateTerminal(Realm realm, String customerId, String entity, TerminalType terminalType) {
        this.id = StringUtil.uuid();
        this.realm = realm;
        this.customerId = customerId;
        this.entity = entity;
        this.terminalType = terminalType;
    }
}
