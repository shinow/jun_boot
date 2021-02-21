package com.ruoyi.front.zcool.mapper;

import com.ruoyi.front.zcool.domain.Message;
import com.ruoyi.front.zcool.domain.ZcoolUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface MessageMapper {

    public List<Message> selectByMsgType(@Param("msgType") String msgType,@Param("userId") String userId);

    public List<Message> selectByAllType(@Param("userId") String userId);

    public int updateReadFlag(String[] ids);

    public Map countMessages(String userId);
}
