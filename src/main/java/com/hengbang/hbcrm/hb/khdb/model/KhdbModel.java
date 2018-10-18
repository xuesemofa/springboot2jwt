package com.hengbang.hbcrm.hb.khdb.model;

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
public class KhdbModel implements Serializable {

    private String uuid;
    //调拨人
    private String dbr;
    //    调拨时间
    private Timestamp dbsj;
    //    调出方
    private String dcf;
    //    调入方
    private String drf;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDbr() {
        return dbr;
    }

    public void setDbr(String dbr) {
        this.dbr = dbr;
    }

    public Timestamp getDbsj() {
        return dbsj;
    }

    public void setDbsj(Timestamp dbsj) {
        this.dbsj = dbsj;
    }

    public String getDcf() {
        return dcf;
    }

    public void setDcf(String dcf) {
        this.dcf = dcf;
    }

    public String getDrf() {
        return drf;
    }

    public void setDrf(String drf) {
        this.drf = drf;
    }

    public KhdbModel() {
        super();
    }

    public KhdbModel(String uuid, String dbr, Timestamp dbsj, String dcf, String drf) {
        this.uuid = uuid;
        this.dbr = dbr;
        this.dbsj = dbsj;
        this.dcf = dcf;
        this.drf = drf;
    }

    @Override
    public String toString() {
        return "KhdbModel{" +
                "uuid='" + uuid + '\'' +
                ", dbr='" + dbr + '\'' +
                ", dbsj=" + dbsj +
                ", dcf='" + dcf + '\'' +
                ", drf='" + drf + '\'' +
                '}';
    }
}
