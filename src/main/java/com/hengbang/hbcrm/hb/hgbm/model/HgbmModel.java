package com.hengbang.hbcrm.hb.hgbm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HgbmModel implements Serializable {
    private String uuid;
    //    父级
    @NotBlank(message = "父级不能为空")
    private String parents;
    //    名称
    @NotBlank(message = "名称不能为空")
    @Size(max = 200, message = "名称最大长度不能超过200位")
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

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public HgbmModel() {
        super();
    }

    public HgbmModel(String uuid, String parents, String mc) {
        this.uuid = uuid;
        this.parents = parents;
        this.mc = mc;
    }

    @Override
    public String toString() {
        return "HgbmModel{" +
                "uuid='" + uuid + '\'' +
                ", parents='" + parents + '\'' +
                ", mc='" + mc + '\'' +
                '}';
    }
}
