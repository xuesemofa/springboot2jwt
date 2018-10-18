package com.hengbang.hbcrm.hb.lxfs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LxfsModel implements Serializable {

    private String uuid;
    //  姓名
    @NotBlank(message = "姓名不能为空")
    private String xm;
    //    职位
    private String zw;
    //    电话
    @NotBlank(message = "电话不能为空")
    private String dh;
    //    电子邮件
    private String dzyj;
    //    阿里旺旺
    private String alww;
    //    钉钉
    private String dd;
    //    婚否
    private String hf;
    //    性格
    private String xg;
    //    性别
    private String xb;
    //    生日
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date sr;
    //    传真
    private String cz;
    //    手机号
    private String sjh;
    //    msn/qq
    private String qq;
    //    其它联系方式
    private String qtlxfs;
    //    家庭成员
    private String jtcy;
    //    重要日子
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date zyrz;
    //    顺序号
    private int ssh;
    //    公司主键
    private String cusid;
    //    图片
    private String imgs;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getDzyj() {
        return dzyj;
    }

    public void setDzyj(String dzyj) {
        this.dzyj = dzyj;
    }

    public String getAlww() {
        return alww;
    }

    public void setAlww(String alww) {
        this.alww = alww;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getHf() {
        return hf;
    }

    public void setHf(String hf) {
        this.hf = hf;
    }

    public String getXg() {
        return xg;
    }

    public void setXg(String xg) {
        this.xg = xg;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public Date getSr() {
        return sr;
    }

    public void setSr(Date sr) {
        this.sr = sr;
    }

    public String getCz() {
        return cz;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQtlxfs() {
        return qtlxfs;
    }

    public void setQtlxfs(String qtlxfs) {
        this.qtlxfs = qtlxfs;
    }

    public String getJtcy() {
        return jtcy;
    }

    public void setJtcy(String jtcy) {
        this.jtcy = jtcy;
    }

    public Date getZyrz() {
        return zyrz;
    }

    public void setZyrz(Date zyrz) {
        this.zyrz = zyrz;
    }

    public int getSsh() {
        return ssh;
    }

    public void setSsh(int ssh) {
        this.ssh = ssh;
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

    public LxfsModel() {
        super();
    }

    public LxfsModel(String uuid, String xm, String zw, String dh, String dzyj, String alww, String dd, String hf, String xg, String xb, Date sr, String cz, String sjh, String qq, String qtlxfs, String jtcy, Date zyrz, int ssh, String cusid, String imgs) {
        this.uuid = uuid;
        this.xm = xm;
        this.zw = zw;
        this.dh = dh;
        this.dzyj = dzyj;
        this.alww = alww;
        this.dd = dd;
        this.hf = hf;
        this.xg = xg;
        this.xb = xb;
        this.sr = sr;
        this.cz = cz;
        this.sjh = sjh;
        this.qq = qq;
        this.qtlxfs = qtlxfs;
        this.jtcy = jtcy;
        this.zyrz = zyrz;
        this.ssh = ssh;
        this.cusid = cusid;
        this.imgs = imgs;
    }

    @Override
    public String toString() {
        return "LxfsModel{" +
                "uuid='" + uuid + '\'' +
                ", xm='" + xm + '\'' +
                ", zw='" + zw + '\'' +
                ", dh='" + dh + '\'' +
                ", dzyj='" + dzyj + '\'' +
                ", alww='" + alww + '\'' +
                ", dd='" + dd + '\'' +
                ", hf='" + hf + '\'' +
                ", xg='" + xg + '\'' +
                ", xb='" + xb + '\'' +
                ", sr=" + sr +
                ", cz='" + cz + '\'' +
                ", sjh='" + sjh + '\'' +
                ", qq='" + qq + '\'' +
                ", qtlxfs='" + qtlxfs + '\'' +
                ", jtcy='" + jtcy + '\'' +
                ", zyrz=" + zyrz +
                ", ssh=" + ssh +
                ", cusid='" + cusid + '\'' +
                ", imgs='" + imgs + '\'' +
                '}';
    }
}
