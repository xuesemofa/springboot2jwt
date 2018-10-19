package com.springboot.crm.business.gjjl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GjjlModel implements Serializable {

    private String uuid;

    //    客户主键
    @NotBlank(message = "客户不能为空")
    private String cusid;
    //    跟进时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gjsj;
    //    跟进人(当前登陆人)
    private String gjr;
    //    跟进方式
    @NotBlank(message = "请选择跟进方式")
    private String gjfs;
    //    联系人
    @NotBlank(message = "请选择联系人")
    private String lxr;
    //    跟进情况
    @NotBlank(message = "请填写跟进情况")
    @Size(min = 1, max = 200, message = "跟进情况最大输入位数为200内")
    private String gjqk;
    //    领导审阅（保留）
    private String ldsy;

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

    public Date getGjsj() {
        return gjsj;
    }

    public void setGjsj(Date gjsj) {
        this.gjsj = gjsj;
    }

    public String getGjr() {
        return gjr;
    }

    public void setGjr(String gjr) {
        this.gjr = gjr;
    }

    public String getGjfs() {
        return gjfs;
    }

    public void setGjfs(String gjfs) {
        this.gjfs = gjfs;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getGjqk() {
        return gjqk;
    }

    public void setGjqk(String gjqk) {
        this.gjqk = gjqk;
    }

    public String getLdsy() {
        return ldsy;
    }

    public void setLdsy(String ldsy) {
        this.ldsy = ldsy;
    }

    public GjjlModel() {
        super();
    }

    public GjjlModel(String uuid, String cusid, Date gjsj, String gjr, String gjfs, String lxr, String gjqk, String ldsy) {
        this.uuid = uuid;
        this.cusid = cusid;
        this.gjsj = gjsj;
        this.gjr = gjr;
        this.gjfs = gjfs;
        this.lxr = lxr;
        this.gjqk = gjqk;
        this.ldsy = ldsy;
    }

    @Override
    public String toString() {
        return "GjjlModel{" +
                "uuid='" + uuid + '\'' +
                ", cusid='" + cusid + '\'' +
                ", gjsj=" + gjsj +
                ", gjr='" + gjr + '\'' +
                ", gjfs='" + gjfs + '\'' +
                ", lxr='" + lxr + '\'' +
                ", gjqk='" + gjqk + '\'' +
                ", ldsy='" + ldsy + '\'' +
                '}';
    }
}
