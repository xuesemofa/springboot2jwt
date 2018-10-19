package com.springboot.crm.business.khslxz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KhslxzModel implements Serializable {

    private String uuid;
    private String lx;
    @Digits(integer = 99999, fraction = 0, message = "数量为整数")
    @Max(value = 99999, message = "数量最大值为99999")
    private int sl;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public KhslxzModel() {
        super();
    }

    public KhslxzModel(String uuid, String lx, int sl) {
        this.uuid = uuid;
        this.lx = lx;
        this.sl = sl;
    }

    @Override
    public String toString() {
        return "KhslxzModel{" +
                "uuid='" + uuid + '\'' +
                ", lx='" + lx + '\'' +
                ", sl=" + sl +
                '}';
    }
}
