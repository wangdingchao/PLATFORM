package com.example.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public Page<GameEvent> findPage(int page,int size) throws BizException {
        return gameEventMapper.selectPage(new Page<>(page,size),null);
    }

    @Override
    public void addGameEvent(GameEvent gameEvent) {
        gameEventMapper.insert(gameEvent);
    }

    @Override
    public void updateGameEventById(GameEvent gameEvent) {
        gameEventMapper.updateById(gameEvent);
    }

    @Override
    public List<GameEvent> findAll() {
        return gameEventMapper.selectList(null);
    }

    @Override
    public GameEvent findById(Integer id) {
        return gameEventMapper.selectById(id);
    }

    @Override
    public void deleteGameEventById(Integer id) {
        gameEventMapper.deleteById(id);
    }
}
