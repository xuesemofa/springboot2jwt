package com.springboot.crm.business.bmgl.model;

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
public class BmglModel implements Serializable {

    private String uuid;

    private String parents;

    @NotBlank(message = "团队名称不能为空")
    private String mc;

    private String bla;

    private String blb;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getBla() {
        return bla;
    }

    public void setBla(String bla) {
        this.bla = bla;
    }

    public String getBlb() {
        return blb;
    }

    public void setBlb(String blb) {
        this.blb = blb;
    }

    public BmglModel() {
        super();
    }

    public BmglModel(String uuid, String parents, String mc, String bla, String blb) {
        this.uuid = uuid;
        this.parents = parents;
        this.mc = mc;
        this.bla = bla;
        this.blb = blb;
    }

    @Override
    public String toString() {
        return "BmglModel{" +
                "uuid='" + uuid + '\'' +
                ", parents='" + parents + '\'' +
                ", mc='" + mc + '\'' +
                ", bla='" + bla + '\'' +
                ", blb='" + blb + '\'' +
                '}';
    }
}
