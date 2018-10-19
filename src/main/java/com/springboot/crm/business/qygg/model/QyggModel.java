package com.springboot.crm.business.qygg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QyggModel implements Serializable {

    private String uuid;

    private String titles;

    private String bodys;

    private String lx;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp systimes;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getBodys() {
        return bodys;
    }

    public void setBodys(String bodys) {
        this.bodys = bodys;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public Timestamp getSystimes() {
        return systimes;
    }

    public void setSystimes(Timestamp systimes) {
        this.systimes = systimes;
    }

    public QyggModel() {
        super();
    }

    public QyggModel(String uuid, String titles, String bodys, String lx, Timestamp systimes) {
        this.uuid = uuid;
        this.titles = titles;
        this.bodys = bodys;
        this.lx = lx;
        this.systimes = systimes;
    }

    @Override
    public String toString() {
        return "QyggModel{" +
                "uuid='" + uuid + '\'' +
                ", titles='" + titles + '\'' +
                ", bodys='" + bodys + '\'' +
                ", lx='" + lx + '\'' +
                ", systimes=" + systimes +
                '}';
    }
}
