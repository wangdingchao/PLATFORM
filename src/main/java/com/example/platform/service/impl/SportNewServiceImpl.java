package com.example.platform.service.impl;

import com.example.platform.exception.BizException;
import com.example.platform.mapper.SportNewMapper;
import com.example.platform.pojo.SportNew;
import com.example.platform.service.SportNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shuai Fang
 * @version 1.0
 * @description 新闻service implementation
 * @date 2023/10/12 18:01
 */

@Service
public class SportNewServiceImpl implements SportNewService {

    @Autowired
    private SportNewMapper sportNewMapper;

    // 分页查询体育新闻
    // query sport news by page
//    @Override
//    public Page<SportNew> findSportNewPage(int page, int size) throws BizException {
//        return sportNewMapper.selectPage(new Page<>(page, size), null);
//    }
//
//    // 添加一个体育新闻
//    // Add a sport new
//    @Override
//    public void addSportNew(SportNew sportNew) {
//        sportNewMapper.insert(sportNew);
//    }
//
//    // 更新一个体育新闻
//    // Update a sport new
//    @Override
//    public void updateSportNewById(SportNew sportNew) {
//        sportNewMapper.updateById(sportNew);
//    }
//
//    // 查询所有的新闻，不分页
//    // Query all sport news
//    @Override
//    public List<SportNew> findAllSportNew() {
//        return sportNewMapper.selectList(null);
//    }
//
//    // 通过id查询一条新闻
//    // Query a sport new by id
//    @Override
//    public SportNew findSportNewById(Integer snId) {
//        return sportNewMapper.selectById(snId);
//    }
//
//    // 删除一条新闻
//    // Delete a sport new by id
//    @Override
//    public void deleteSportNewById(Integer snId) {
//        sportNewMapper.deleteById(snId);
//    }
}
