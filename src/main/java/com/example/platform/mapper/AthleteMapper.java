package com.example.platform.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.platform.pojo.Athlete;

public interface AthleteMapper  {

    // 查询用户详情
    // Query athlete's details
    Athlete findDesc(Integer aid);


}
