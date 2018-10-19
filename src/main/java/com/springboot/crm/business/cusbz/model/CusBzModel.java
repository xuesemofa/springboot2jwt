package com.springboot.crm.business.cusbz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CusBzModel implements Serializable {

    private String uuid;
    //    备注人
    private String bzr;
    //    备注日期
    private Timestamp bzrq;
    //    备注内容
    private String bznr;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBzr() {
        return bzr;
    }

    public void setBzr(String bzr) {
        this.bzr = bzr;
    }

    public Timestamp getBzrq() {
        return bzrq;
    }

    public void setBzrq(Timestamp bzrq) {
        this.bzrq = bzrq;
    }

    public String getBznr() {
        return bznr;
    }

    public void setBznr(String bznr) {
        this.bznr = bznr;
    }

    public CusBzModel() {
        super();
    }

    public CusBzModel(String uuid, String bzr, Timestamp bzrq, String bznr) {
        this.uuid = uuid;
        this.bzr = bzr;
        this.bzrq = bzrq;
        this.bznr = bznr;
    }

    @Override
    public String toString() {
        return "CusBzModel{" +
                "uuid='" + uuid + '\'' +
                ", bzr='" + bzr + '\'' +
                ", bzrq=" + bzrq +
                ", bznr='" + bznr + '\'' +
                '}';
    }
}
