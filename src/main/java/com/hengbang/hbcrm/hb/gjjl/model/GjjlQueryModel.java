package com.hengbang.hbcrm.hb.gjjl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GjjlQueryModel implements Serializable {

    private String uuid;
    //    客户主键
    private String cusid;
    //    跟进时间-开始时间
    private Date gjsjStr;
    //    跟进时间-结束时间
    private Date gjsjEnd;
    //    跟进人
    private String gjr;
    //    跟进方式
    private String gjfs;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public Date getGjsjStr() {
        return gjsjStr;
    }

    public void setGjsjStr(Date gjsjStr) {
        this.gjsjStr = gjsjStr;
    }

    public Date getGjsjEnd() {
        return gjsjEnd;
    }

    public void setGjsjEnd(Date gjsjEnd) {
        this.gjsjEnd = gjsjEnd;
    }

    public String getGjr() {
        return gjr;
    }

    public void setGjr(String gjr) {
        this.gjr = gjr;
    }

    public String getGjfs() {
        return gjfs;
    }

    public void setGjfs(String gjfs) {
        this.gjfs = gjfs;
    }

    public GjjlQueryModel() {
        super();
    }

    public GjjlQueryModel(String uuid, String cusid, Date gjsjStr, Date gjsjEnd, String gjr, String gjfs) {
        this.uuid = uuid;
        this.cusid = cusid;
        this.gjsjStr = gjsjStr;
        this.gjsjEnd = gjsjEnd;
        this.gjr = gjr;
        this.gjfs = gjfs;
    }

    @Override
    public String toString() {
        return "GjjlQueryModel{" +
                "uuid='" + uuid + '\'' +
                ", cusid='" + cusid + '\'' +
                ", gjsjStr=" + gjsjStr +
                ", gjsjEnd=" + gjsjEnd +
                ", gjr='" + gjr + '\'' +
                ", gjfs='" + gjfs + '\'' +
                '}';
    }
}
