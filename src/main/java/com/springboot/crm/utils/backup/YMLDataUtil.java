package com.springboot.crm.utils.backup;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@ConfigurationProperties(prefix = "blackup")
@Component
public class YMLDataUtil implements Serializable {

    private String mysqldump;

    public String getMysqldump() {
        return mysqldump;
    }

    public void setMysqldump(String mysqldump) {
        this.mysqldump = mysqldump;
    }

}
