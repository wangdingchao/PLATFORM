package com.example.platform.service.impl;

import com.example.platform.exception.BizException;
import com.example.platform.mapper.AthleteMapper;
import com.example.platform.pojo.Athlete;
import com.example.platform.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteServiceImpl implements AthleteService {

//    @Autowired
//    private AthleteMapper athleteMapper;
//
//    @Override
//    public Page<Athlete> findAthletePage(int page, int size) throws BizException {
//        return athleteMapper.selectPage(new Page<>(page, size), null);
//    }
//
//    @Override
//    public void addAthlete(Athlete athlete) {
//        athleteMapper.insert(athlete);
//    }
//
//    @Override
//    public void updateAthleteById(Athlete athlete) {
//        athleteMapper.updateById(athlete);
//    }
//
//    @Override
//    public List<Athlete> findAllAthlete() {
//        return athleteMapper.selectList(null);
//    }
//
//    @Override
//    public Athlete findAthleteById(Integer aid) {
//        return athleteMapper.selectById(aid);
//    }
//
//    @Override
//    public void deleteAthleteById(Integer aid) {
//        athleteMapper.deleteById(aid);
//    }


}
