package com.example.platform.service;

import com.example.platform.dto.PagedList;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.GameEvent;

import java.util.List;

/**
 * @author wdc
 * @date 2023/10/10
 * @description
 */
public interface GameEventService {


    PagedList<GameEvent> findPage(String title,int page, int size) throws BizException;
//
//    void addGameEvent(GameEvent gameEvent);
//
//    void updateGameEventById(GameEvent gameEvent);
//
//    List<GameEvent> findAll();
//
//    GameEvent findById(Integer id);
//
//    void deleteGameEventById(Integer id);
}
