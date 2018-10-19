package com.springboot.crm.utils.backup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
public class ScheduledUtil {

    @Value("${databaseSavePath}")
    private String databaseSvcePath;
    @Value("${blackup.mysqldump}")
    private String mysqldump;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${blackup.databaseName}")
    private String databaseName;
    @Value("${blackup.databaseHost}")
    private String databaseHost;

    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    @Scheduled(cron = "* * 0/1 ? * *")
    public void bf() {
        YMLDataUtil ymlDataUtil = new YMLDataUtil();
        System.out.println(ymlDataUtil.getMysqldump());
        try {
            boolean b = MySQLDatabaseBackup.exportDatabaseTool(databaseHost,
                    username,
                    password,
                    databaseSvcePath,
                    format.format(System.currentTimeMillis()) + ".sql",
                    databaseName,
                    mysqldump);
            if (b) {
                System.out.println("数据库成功备份！！！");
                log.info("数据库成功备份！！！");
            } else {
                System.out.println("数据库备份失败！！！");
                log.info("数据库备份失败！！！");
            }
        } catch (Exception e) {
            System.out.println("数据库备份失败！！！");
            log.info("数据库备份失败！！！");
        }
    }
}
