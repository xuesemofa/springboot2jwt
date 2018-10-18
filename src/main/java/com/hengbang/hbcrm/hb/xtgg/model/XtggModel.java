package com.hengbang.hbcrm.hb.xtgg.model;

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
public class XtggModel implements Serializable {

    private String uuid;
    //    发布人
    private String fbr;
    //    接收人 0：群体 其它值：指定接收人
    private String jsr;
    //    公告内容
    private String ggnr;
    //    公告时间
    private Timestamp ggsj;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFbr() {
        return fbr;
    }

    public void setFbr(String fbr) {
        this.fbr = fbr;
    }

    public String getJsr() {
        return jsr;
    }

    public void setJsr(String jsr) {
        this.jsr = jsr;
    }

    public String getGgnr() {
        return ggnr;
    }

    public void setGgnr(String ggnr) {
        this.ggnr = ggnr;
    }

    public Timestamp getGgsj() {
        return ggsj;
    }

    public void setGgsj(Timestamp ggsj) {
        this.ggsj = ggsj;
    }

    public XtggModel() {
        super();
    }

    public XtggModel(String uuid, String fbr, String jsr, String ggnr, Timestamp ggsj) {
        this.uuid = uuid;
        this.fbr = fbr;
        this.jsr = jsr;
        this.ggnr = ggnr;
        this.ggsj = ggsj;
    }

    @Override
    public String toString() {
        return "XtggModel{" +
                "uuid='" + uuid + '\'' +
                ", fbr='" + fbr + '\'' +
                ", jsr='" + jsr + '\'' +
                ", ggnr='" + ggnr + '\'' +
                ", ggsj=" + ggsj +
                '}';
    }
}
