package com.example.platform.service.impl;

import com.example.platform.mapper.AdministratorMapper;
import com.example.platform.pojo.Administrator;
import com.example.platform.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService {

//    @Autowired
//    public AdministratorMapper administratorMapper;
//    @Override
//    public Page<Administrator> findPage(int page, int size) {
//        Page selectPage = administratorMapper.selectPage(new Page(page, size), null);
//        return selectPage;
//    }
//
//    @Override
//    public void add(Administrator administrator) {
//        administratorMapper.insert(administrator);
//    }
//
//    @Override
//    public Administrator findById(Integer aid) {
//        return administratorMapper.selectById(aid);
//    }


}
