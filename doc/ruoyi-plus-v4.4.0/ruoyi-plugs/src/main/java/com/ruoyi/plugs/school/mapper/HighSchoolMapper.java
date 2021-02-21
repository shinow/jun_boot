package com.ruoyi.plugs.school.mapper;

import java.util.List;
import java.util.Map;

public interface HighSchoolMapper {
    public List<Map<String,Object>> selectSchoolLikeWords(String words);
}
