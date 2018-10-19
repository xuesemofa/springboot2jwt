package com.springboot.crm.business.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ld
 * @name 修改记录
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer3Model implements Serializable {

    private String uuid;

    //    原uuid
    private String yuuid;
    //更改日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp backsystimes;
    //    修改操作人
    private String xgr;
    //    原值
    private String yz;
    //    新值
    private String xz;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getYuuid() {
        return yuuid;
    }

    public void setYuuid(String yuuid) {
        this.yuuid = yuuid;
    }

    public Timestamp getBacksystimes() {
        return backsystimes;
    }

    public void setBacksystimes(Timestamp backsystimes) {
        this.backsystimes = backsystimes;
    }

    public String getXgr() {
        return xgr;
    }

    public void setXgr(String xgr) {
        this.xgr = xgr;
    }

    public String getYz() {
        return yz;
    }

    public void setYz(String yz) {
        this.yz = yz;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public Customer3Model() {
        super();
    }

    public Customer3Model(String uuid, String yuuid, Timestamp backsystimes, String xgr, String yz, String xz) {
        this.uuid = uuid;
        this.yuuid = yuuid;
        this.backsystimes = backsystimes;
        this.xgr = xgr;
        this.yz = yz;
        this.xz = xz;
    }

    @Override
    public String toString() {
        return "Customer3Model{" +
                "uuid='" + uuid + '\'' +
                ", yuuid='" + yuuid + '\'' +
                ", backsystimes=" + backsystimes +
                ", xgr='" + xgr + '\'' +
                ", yz='" + yz + '\'' +
                ", xz='" + xz + '\'' +
                '}';
    }
}
