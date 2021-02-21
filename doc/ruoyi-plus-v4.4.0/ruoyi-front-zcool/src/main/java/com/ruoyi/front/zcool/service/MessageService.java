package com.ruoyi.front.zcool.service;

import com.ruoyi.front.zcool.domain.Message;
import com.ruoyi.front.zcool.mapper.MessageMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public List<Message> selectByMsgType(String msgType,String userId){
        List<Message> list=messageMapper.selectByMsgType(msgType,userId);
        //this.updateReadFlag(list);
       return list;
    }

    public List<Message> selectByAllType(String userId){
        return messageMapper.selectByAllType(userId);
    }

    public int updateReadFlag(List<Message> list){
        if(CollectionUtils.isNotEmpty(list)){
            String[] arr=new String[list.size()];
            for(int i=0;i<list.size();i++){
              arr[i]=String.valueOf(list.get(i).getId());
            }
            return  messageMapper.updateReadFlag(arr);
        }
        return 0;
    }
    public Map countMessages(String userId){
        return messageMapper.countMessages(userId);
    }
}
