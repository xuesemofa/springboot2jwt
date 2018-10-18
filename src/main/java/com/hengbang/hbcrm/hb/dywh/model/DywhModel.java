package com.hengbang.hbcrm.hb.dywh.model;

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
public class DywhModel implements Serializable {

    private String uuid;

    @NotBlank(message = "父级不能为空")
    private String parents;
    private String lx;
    @NotBlank(message = "名称不能为空")
    private String mc;

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

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public DywhModel() {
        super();
    }

    public DywhModel(String uuid, String parents, String lx, String mc) {
        this.uuid = uuid;
        this.parents = parents;
        this.lx = lx;
        this.mc = mc;
    }

    @Override
    public String toString() {
        return "DywhModel{" +
                "uuid='" + uuid + '\'' +
                ", parents='" + parents + '\'' +
                ", lx='" + lx + '\'' +
                ", mc='" + mc + '\'' +
                '}';
    }
}
