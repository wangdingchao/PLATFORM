package com.example.platform.mapper;

import com.example.platform.pojo.GameEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wdc
 * @date 2023/10/10
 * @description
 */

@Mapper
public interface GameEventMapper {

    void addGameEvent(GameEvent gameEvent);

    void updateGameEvent(GameEvent gameEvent);

    GameEvent findGameEventById(@Param("gid") String gid);

    List<GameEvent> findGameEventByCondition(@Param("title") String title,@Param("page") int page,@Param("size") int size);

    int findGameEventCountByCondition(@Param("title") String title);

}
