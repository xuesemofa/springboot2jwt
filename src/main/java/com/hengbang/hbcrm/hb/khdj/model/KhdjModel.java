package com.hengbang.hbcrm.hb.khdj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KhdjModel implements Serializable {

    private String uuid;

    @NotBlank(message = "客户等级名称不能为空")
    private String mc;

    private String flag = "0";

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public KhdjModel() {
        super();
    }

    public KhdjModel(String uuid, String mc, String flag) {
        this.uuid = uuid;
        this.mc = mc;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "KhdjModel{" +
                "uuid='" + uuid + '\'' +
                ", mc='" + mc + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
