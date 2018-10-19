package com.springboot.crm.business.jurisdiction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JurisdictionModel implements Serializable {

    private String uuid;

    private String name;

    private String bs;

    private String parents;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public JurisdictionModel() {
        super();
    }

    public JurisdictionModel(String uuid, String name, String bs, String parents) {
        this.uuid = uuid;
        this.name = name;
        this.bs = bs;
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "JurisdictionModel{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", bs='" + bs + '\'' +
                ", parents='" + parents + '\'' +
                '}';
    }
}
