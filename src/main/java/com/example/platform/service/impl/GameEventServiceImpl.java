package com.example.platform.service.impl;

import com.example.platform.dto.PagedList;
import com.example.platform.exception.BizException;
import com.example.platform.mapper.GameEventMapper;
import com.example.platform.pojo.GameEvent;
import com.example.platform.service.GameEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wdc
 * @date 2023/10/10
 * @description
 */

@Service
public class GameEventServiceImpl implements GameEventService {

    @Autowired
    private GameEventMapper gameEventMapper;

    @Override
    public PagedList<GameEvent> findPage(String title,int page, int size) throws BizException {
        List<GameEvent> gameEvents = gameEventMapper.findGameEventByCondition(title, page, size);
        int count = gameEventMapper.findGameEventCountByCondition(title);
        return new PagedList(gameEvents,count);
    }
//
//    @Override
//    public void addGameEvent(GameEvent gameEvent) {
//        gameEventMapper.insert(gameEvent);
//    }
//
//    @Override
//    public void updateGameEventById(GameEvent gameEvent) {
//        gameEventMapper.updateById(gameEvent);
//    }
//
//    @Override
//    public List<GameEvent> findAll() {
//        return gameEventMapper.selectList(null);
//    }
//
//    @Override
//    public GameEvent findById(Integer id) {
//        return gameEventMapper.selectById(id);
//    }
//
//    @Override
//    public void deleteGameEventById(Integer id) {
//        gameEventMapper.deleteById(id);
//    }
}
