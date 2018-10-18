package com.hengbang.hbcrm.hb.jurisdiction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JurBmModel implements Serializable {

    private String uuid;

    private String bmId;

    private String jurId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBmId() {
        return bmId;
    }

    public void setBmId(String bmId) {
        this.bmId = bmId;
    }

    public String getJurId() {
        return jurId;
    }

    public void setJurId(String jurId) {
        this.jurId = jurId;
    }

    public JurBmModel() {
        super();
    }

    public JurBmModel(String uuid, String bmId, String jurId) {
        this.uuid = uuid;
        this.bmId = bmId;
        this.jurId = jurId;
    }

    @Override
    public String toString() {
        return "JurBmModel{" +
                "uuid='" + uuid + '\'' +
                ", bmId='" + bmId + '\'' +
                ", jurId='" + jurId + '\'' +
                '}';
    }
}
