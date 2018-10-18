package com.hengbang.hbcrm.hb.gjpl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 跟进频率提醒时间设置
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GjplModel implements Serializable {

    private String uuid;
    //    客户类型 1：未签约 2：断约 3：合作
    @Min(value = 1, message = "类型最小值为1")
    @Max(value = 3, message = "类型最大值为3")
    private int lx;
    //    提醒周期
    @Min(value = 1, message = "周期最小值为1")
    @Max(value = 99, message = "周期最大值为99")
    private int zq;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getLx() {
        return lx;
    }

    public void setLx(int lx) {
        this.lx = lx;
    }

    public int getZq() {
        return zq;
    }

    public void setZq(int zq) {
        this.zq = zq;
    }

    public GjplModel() {
        super();
    }

    public GjplModel(String uuid, int lx, int zq) {
        this.uuid = uuid;
        this.lx = lx;
        this.zq = zq;
    }

    @Override
    public String toString() {
        return "GjplModel{" +
                "uuid='" + uuid + '\'' +
                ", lx=" + lx +
                ", zq=" + zq +
                '}';
    }
}
