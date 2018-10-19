package com.springboot.crm.business.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ld
 * @name bei zhu
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CusBwlModel implements Serializable {

    private String uuid;

    private String cusid;

    private String imgs;

    private Timestamp scsj;

    private String accid;

    @NotBlank(message = "备注内容不能为空")
    @Max(value = 200, message = "备注内容长度不能超出200位")
    private String bz;

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

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public CusBwlModel() {
        super();
    }

    public CusBwlModel(String uuid, String cusid, String imgs, Timestamp scsj, String accid, String bz) {
        this.uuid = uuid;
        this.cusid = cusid;
        this.imgs = imgs;
        this.scsj = scsj;
        this.accid = accid;
        this.bz = bz;
    }

    @Override
    public String toString() {
        return "CusBwlModel{" +
                "uuid='" + uuid + '\'' +
                ", cusid='" + cusid + '\'' +
                ", imgs='" + imgs + '\'' +
                ", scsj=" + scsj +
                ", accid='" + accid + '\'' +
                ", bz='" + bz + '\'' +
                '}';
    }
}
