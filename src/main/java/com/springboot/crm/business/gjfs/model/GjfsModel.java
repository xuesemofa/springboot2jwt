package com.springboot.crm.business.gjfs.model;

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
public class GjfsModel implements Serializable {

    private String uuid;

    @NotBlank(message = "跟进方式名称不能为空")
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

    public GjfsModel() {
        super();
    }

    public GjfsModel(String uuid, String mc, String flag) {
        this.uuid = uuid;
        this.mc = mc;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "GjfsModel{" +
                "uuid='" + uuid + '\'' +
                ", mc='" + mc + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
