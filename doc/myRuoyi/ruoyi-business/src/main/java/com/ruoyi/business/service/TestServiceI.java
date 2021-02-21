package com.ruoyi.business.service;

import java.util.List;
import java.util.Map;

/**
 * @author PanMeiCheng
 * @version 1.0
 * @date 2018-12-11
 * @company DM
 */
public interface TestServiceI {
    List<Map> getAllUsers();
    List<Map> getAllUsers(String sql);
}
