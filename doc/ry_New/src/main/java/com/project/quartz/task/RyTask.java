package com.project.quartz.task;

import com.project.common.config.Global;
import com.project.common.utils.TimeUuidUtil;
import com.project.quartz.config.DbParamConfig;
import com.project.quartz.service.ISysJobService;
import com.project.quartz.util.BatCommandUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 定时任务调度测试
 *
 * @author lws
 */
@Component("ryTask")
public class RyTask {
    private static final Logger log = LoggerFactory.getLogger(RyTask.class);
    @Autowired
    DbParamConfig dbParamConfig;
    @Autowired
    ISysJobService jobService;
    private static String mysqlBasedir = null;

    private void initMysqlDir(){
        if(mysqlBasedir==null){
            mysqlBasedir = jobService.getMysqlBasedir();
        }
    }

    public void recoverDBFile(String fileId) {
        log.info("------------------执行定时任务------------ryTask.ryParams，参数:"+fileId);
        if(fileId.indexOf(".sql")==-1){
            fileId = fileId+".sql";
        }
        try {
            File sqlFile = new File(Global.getDbBackupPath()+fileId);
            if(!sqlFile.exists()){
                log.error("数据库恢复时录入的文件id值不存在："+fileId);
                return;
            }
            initMysqlDir();
            importDBFile(sqlFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("执行数数据库恢复时发生异常："+e.getMessage());
        }
    }
    public void backupDB() {
        log.info("------------------执行定时任务------------ryTask.ryNoParams");
        try {
            initMysqlDir();
            backup(Global.getDbBackupPath());
        } catch (Exception e) {
            log.error("执行数据库备份时发生异常："+e.getMessage());
        }
    }
    //===============================================内部方法=========================================
    private void importDBFile(String filePath) throws IOException{
        StringBuilder cmdStr = new StringBuilder();
        cmdStr.append(" -u").append(dbParamConfig.getUsername())
                .append(" -p").append(dbParamConfig.getPassword())
                .append(" -h").append(dbParamConfig.getHost())
                .append(" -P").append(dbParamConfig.getPort())
                .append(" ").append(dbParamConfig.getDatabaseName())
                .append("<").append(filePath);
        if (isWindows()) {
            BatCommandUtil.writeAndExcute(Global.getDbBackupPath()+"recoverMysqlDb.bat",new StringBuilder()
                    .append("cd /d ").append(mysqlBasedir).append("bin").append("\r\n")
                    .append("mysql").append(cmdStr.toString()).toString());
        } else {
            throw new RuntimeException("暂时只支持windows系统！");
        }
    }
    /**
     * 备份数据库操作
     *
     * @param filepath 文保存件路径
     * @return
     */
    private void backup(String filepath) throws IOException{
        File sqlFile = BatCommandUtil.createFile(filepath+ TimeUuidUtil.get16UUID()+".sql");
        StringBuilder cmdStr = new StringBuilder();
        // 构建数据库备份参数
        cmdStr.append(" -u").append(dbParamConfig.getUsername())
                .append(" -p").append(dbParamConfig.getPassword())
                .append(" -h").append(dbParamConfig.getHost())
                .append(" -P").append(dbParamConfig.getPort())
                .append(" ").append(dbParamConfig.getDatabaseName())
                .append(" ").append("--hex-blob")//使用十六进制符号转储二进制字符序列，防止乱码
                .append(" > ").append(sqlFile.getPath());
        if (isWindows()) {
            BatCommandUtil.writeAndExcute(filepath+"backupmysql.bat",new StringBuilder()
                    .append("cd /d ").append(mysqlBasedir).append("bin").append("\r\n")
                    .append("mysqldump").append(cmdStr.toString()).toString());
        } else {
            throw new RuntimeException("暂时只支持windows系统！");
        }
    }


    private static boolean isWindows() {
        return System.getProperty("os.name").indexOf("Windows") != -1;
    }

}
