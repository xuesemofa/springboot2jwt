package com.hengbang.hbcrm.hb.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ld
 * @name 移入公海记录
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class YrghJlModel implements Serializable {

    private String uuid;
    //       操作人
    private String czr;
    //    原归属人
    private String ygsr;
    //    操作时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp systimes;
    //    调动后归属人
    private String ddhgsr;
    //    被调动的客户
    private String bdddkh;
    //    调动原因
    private String ddyy;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public String getYgsr() {
        return ygsr;
    }

    public void setYgsr(String ygsr) {
        this.ygsr = ygsr;
    }

    public Timestamp getSystimes() {
        return systimes;
    }

    public void setSystimes(Timestamp systimes) {
        this.systimes = systimes;
    }

    public String getDdhgsr() {
        return ddhgsr;
    }

    public void setDdhgsr(String ddhgsr) {
        this.ddhgsr = ddhgsr;
    }

    public String getBdddkh() {
        return bdddkh;
    }

    public void setBdddkh(String bdddkh) {
        this.bdddkh = bdddkh;
    }

    public String getDdyy() {
        return ddyy;
    }

    public void setDdyy(String ddyy) {
        this.ddyy = ddyy;
    }

    public YrghJlModel() {
        super();
    }

    public YrghJlModel(String uuid, String czr, String ygsr, Timestamp systimes, String ddhgsr, String bdddkh, String ddyy) {
        this.uuid = uuid;
        this.czr = czr;
        this.ygsr = ygsr;
        this.systimes = systimes;
        this.ddhgsr = ddhgsr;
        this.bdddkh = bdddkh;
        this.ddyy = ddyy;
    }

    @Override
    public String toString() {
        return "YrghJlModel{" +
                "uuid='" + uuid + '\'' +
                ", czr='" + czr + '\'' +
                ", ygsr='" + ygsr + '\'' +
                ", systimes=" + systimes +
                ", ddhgsr='" + ddhgsr + '\'' +
                ", bdddkh='" + bdddkh + '\'' +
                ", ddyy='" + ddyy + '\'' +
                '}';
    }
}
