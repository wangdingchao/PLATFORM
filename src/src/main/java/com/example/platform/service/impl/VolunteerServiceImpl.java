package com.example.platform.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.platform.exception.BizException;
import com.example.platform.mapper.VolunteerMapper;
import com.example.platform.pojo.Volunteer;
import com.example.platform.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Override
    public Page<Volunteer> findVolunteerPage(int page, int size) throws BizException {
        return volunteerMapper.selectPage(new Page<>(page, size), null);
    }

    @Override
    public void addVolunteer(Volunteer volunteer) {
        volunteerMapper.insert(volunteer);
    }

    @Override
    public void updateVolunteerById(Volunteer volunteer) {
        volunteerMapper.updateById(volunteer);
    }

    @Override
    public List<Volunteer> findAllVolunteer() {
        return volunteerMapper.selectList(null);
    }

    @Override
    public Volunteer findVolunteerById(Integer vid) {
        return volunteerMapper.selectById(vid);
    }

    @Override
    public void deleteVolunteerById(Integer vid) {
        volunteerMapper.deleteById(vid);
    }
}
