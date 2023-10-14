package com.example.platform.mapper;

import com.example.platform.pojo.OperateTerminal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 作者：zhaoxin
 * 最后修改时间：2018-10-25
 * 描述：操作终端记录表mapper
 */
@Mapper
public interface CommonTerminalMapper {

    void insertOperateTerminal(OperateTerminal operateTerminal);

    void deleteOperateTerminal(@Param("id") String id);

    OperateTerminal queryOperateTerminal(@Param("id") String id);
}
