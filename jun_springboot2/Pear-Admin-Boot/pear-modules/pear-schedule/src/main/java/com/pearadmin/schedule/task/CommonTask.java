package com.pearadmin.schedule.task;

import com.pearadmin.schedule.handler.base.BaseTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Describe: 示例任务
 * Author: 就免仪式
 * CreateTime: 2019/10/23
 * */
@Slf4j
@Component("commonTask")
public class CommonTask implements BaseTaskService {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

    /**
     * 任务实现
     * */
    @Override
    public void run(String params) {
        log.info("Params === >> " + params);
        log.info("当前时间::::" + format.format(new Date()));
        System.out.println("执行成功");
    }
}
