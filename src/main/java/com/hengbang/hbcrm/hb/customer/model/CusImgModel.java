package com.hengbang.hbcrm.hb.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CusImgModel implements Serializable {

    private String uuid;

    private String cusid;

    private String lx;

    private String imgs;

    private Timestamp scsj;

    private String accid;

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

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Timestamp getScsj() {
        return scsj;
    }

    public void setScsj(Timestamp scsj) {
        this.scsj = scsj;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public CusImgModel() {
        super();
    }

    public CusImgModel(String uuid, String cusid, String lx, String imgs, Timestamp scsj, String accid) {
        this.uuid = uuid;
        this.cusid = cusid;
        this.lx = lx;
        this.imgs = imgs;
        this.scsj = scsj;
        this.accid = accid;
    }

    @Override
    public String toString() {
        return "CusImgModel{" +
                "uuid='" + uuid + '\'' +
                ", cusid='" + cusid + '\'' +
                ", lx='" + lx + '\'' +
                ", imgs='" + imgs + '\'' +
                ", scsj=" + scsj +
                ", accid='" + accid + '\'' +
                '}';
    }
}
