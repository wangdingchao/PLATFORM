package com.example.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.platform.exception.BizException;
import com.example.platform.pojo.SportNew;

import java.util.List;

/**
 * @author Shuai Fang
 * @version 1.0
 * @description TODO
 * @date 2023/10/12 17:41
 */
public interface SportNewService {
    Page<SportNew> findSportNewPage(int page, int size) throws BizException;

    void addSportNew(SportNew sportNew);

    void updateSportNewById(SportNew sportNew);

    List<SportNew> findAllSportNew();

    SportNew findSportNewById(Integer nid);

    void deleteSportNewById(Integer nid);
}
