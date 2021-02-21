package com.ruoyi.plugs.school.service;

import com.ruoyi.plugs.school.mapper.HighSchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HighSchoolService {

    @Autowired
    HighSchoolMapper highSchoolMapper;

    public List<Map<String,Object>> selectSchoolLikeWords(String words){
        return  highSchoolMapper.selectSchoolLikeWords(words);
    }
}
