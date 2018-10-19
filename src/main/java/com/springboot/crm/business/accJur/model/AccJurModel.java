package com.springboot.crm.business.accJur.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccJurModel implements Serializable {

    private String uuid;

    private String accId;

    private String jurId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getJurId() {
        return jurId;
    }

    public void setJurId(String jurId) {
        this.jurId = jurId;
    }

    public AccJurModel() {
        super();
    }

    public AccJurModel(String uuid, String accId, String jurId) {
        this.uuid = uuid;
        this.accId = accId;
        this.jurId = jurId;
    }

    @Override
    public String toString() {
        return "AccJurModel{" +
                "uuid='" + uuid + '\'' +
                ", accId='" + accId + '\'' +
                ", jurId='" + jurId + '\'' +
                '}';
    }
}
