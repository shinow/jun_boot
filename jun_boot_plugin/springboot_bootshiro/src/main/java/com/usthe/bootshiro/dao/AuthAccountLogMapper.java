package com.usthe.bootshiro.dao;

import com.usthe.bootshiro.domain.bo.AuthAccountLog;
import org.springframework.dao.DataAccessException;

import java.util.List;

/* *
 * @author Wujun
 * @Description 
 * @Date 8:27 2018/4/22
 */
public interface AuthAccountLogMapper {

    List<AuthAccountLog> selectAccountLogList();

    int insertSelective(AuthAccountLog authAccountLog) throws DataAccessException;

}
