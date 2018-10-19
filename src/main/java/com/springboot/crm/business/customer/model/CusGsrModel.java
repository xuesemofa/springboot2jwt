package com.springboot.crm.business.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CusGsrModel implements Serializable {

    private String uuid;

    private String gsr;

    private String bz;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGsr() {
        return gsr;
    }

    public void setGsr(String gsr) {
        this.gsr = gsr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public CusGsrModel() {
        super();
    }

    public CusGsrModel(String uuid, String gsr, String bz) {
        this.uuid = uuid;
        this.gsr = gsr;
        this.bz = bz;
    }

    @Override
    public String toString() {
        return "CusGsrModel{" +
                "uuid='" + uuid + '\'' +
                ", gsr='" + gsr + '\'' +
                ", bz='" + bz + '\'' +
                '}';
    }
}
