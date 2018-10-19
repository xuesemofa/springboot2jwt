package com.springboot.crm.business.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ld
 * @name 冲突条件为公司名称
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer2Model implements Serializable {

    private String uuid;

    //    原uuid
    private String yuuid;
    //    公司名称
    private String gsmc;
    //    省份
    private String sf;
    //    地区
    private String dq;
    //    详细地址
    private String xsdz;
    //    客户类型
    private String khlx;
    //    客户来源
    private String khly;
    //    客户等级
    private String khdj;
    //    成立时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date clsj = null;
    //    主营产品
    private String zycp;
    //    销售区域
    private String xsqy;
    //    员工人数
    private String ygrs;
    //    城市
    private String cs;
    //    企业类型
    private String qylx;
    //    品牌
    private String pp;
    //    网站
    private String wz;
    //    年出口额
    private BigDecimal ncke = new BigDecimal(0);
    //    年订单额
    private BigDecimal ndde = new BigDecimal(0);
    //    商业登记号
    private String sydjh;
    //    销售渠道
    private String xsqd;
    //    财务-名称
    private String cw_mc;
    //    纳税人识别号
    private String cw_nsrsbh;
    //    财务-地址
    private String cw_dz;
    //    财务-电话
    private String cw_dh;
    //    财务-开户行
    private String cw_khh;
    //    财务-账号
    private String cw_zh;
    //    0：正常数据 -1：删除数据
    private String flag = "0";

    //    归属人 公海的为0
    private String gsr;
    //创建日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp system;
    //编号
    private long bh;
    //更改日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp backsystimes;
    //    修改操作人
    private String xgr;

    //    最后一次跟进时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date zhgjsj;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getYuuid() {
        return yuuid;
    }

    public void setYuuid(String yuuid) {
        this.yuuid = yuuid;
    }

    public String getGsmc() {
        return gsmc;
    }

    public void setGsmc(String gsmc) {
        this.gsmc = gsmc;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getDq() {
        return dq;
    }

    public void setDq(String dq) {
        this.dq = dq;
    }

    public String getXsdz() {
        return xsdz;
    }

    public void setXsdz(String xsdz) {
        this.xsdz = xsdz;
    }

    public String getKhly() {
        return khly;
    }

    public void setKhly(String khly) {
        this.khly = khly;
    }

    public String getKhdj() {
        return khdj;
    }

    public void setKhdj(String khdj) {
        this.khdj = khdj;
    }

    public Date getClsj() {
        return clsj;
    }

    public void setClsj(Date clsj) {
        this.clsj = clsj;
    }

    public String getZycp() {
        return zycp;
    }

    public void setZycp(String zycp) {
        this.zycp = zycp;
    }

    public String getXsqy() {
        return xsqy;
    }

    public void setXsqy(String xsqy) {
        this.xsqy = xsqy;
    }

    public String getYgrs() {
        return ygrs;
    }

    public void setYgrs(String ygrs) {
        this.ygrs = ygrs;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getQylx() {
        return qylx;
    }

    public void setQylx(String qylx) {
        this.qylx = qylx;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }

    public BigDecimal getNcke() {
        return ncke;
    }

    public void setNcke(BigDecimal ncke) {
        this.ncke = ncke;
    }

    public BigDecimal getNdde() {
        return ndde;
    }

    public void setNdde(BigDecimal ndde) {
        this.ndde = ndde;
    }

    public String getSydjh() {
        return sydjh;
    }

    public void setSydjh(String sydjh) {
        this.sydjh = sydjh;
    }

    public String getXsqd() {
        return xsqd;
    }

    public void setXsqd(String xsqd) {
        this.xsqd = xsqd;
    }

    public String getCw_mc() {
        return cw_mc;
    }

    public void setCw_mc(String cw_mc) {
        this.cw_mc = cw_mc;
    }

    public String getCw_nsrsbh() {
        return cw_nsrsbh;
    }

    public void setCw_nsrsbh(String cw_nsrsbh) {
        this.cw_nsrsbh = cw_nsrsbh;
    }

    public String getCw_dz() {
        return cw_dz;
    }

    public void setCw_dz(String cw_dz) {
        this.cw_dz = cw_dz;
    }

    public String getCw_dh() {
        return cw_dh;
    }

    public void setCw_dh(String cw_dh) {
        this.cw_dh = cw_dh;
    }

    public String getCw_khh() {
        return cw_khh;
    }

    public void setCw_khh(String cw_khh) {
        this.cw_khh = cw_khh;
    }

    public String getCw_zh() {
        return cw_zh;
    }

    public void setCw_zh(String cw_zh) {
        this.cw_zh = cw_zh;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGsr() {
        return gsr;
    }

    public void setGsr(String gsr) {
        this.gsr = gsr;
    }

    public Timestamp getSystem() {
        return system;
    }

    public void setSystem(Timestamp system) {
        this.system = system;
    }

    public long getBh() {
        return bh;
    }

    public void setBh(long bh) {
        this.bh = bh;
    }

    public Timestamp getBacksystimes() {
        return backsystimes;
    }

    public void setBacksystimes(Timestamp backsystimes) {
        this.backsystimes = backsystimes;
    }

    public String getXgr() {
        return xgr;
    }

    public void setXgr(String xgr) {
        this.xgr = xgr;
    }

    public Date getZhgjsj() {
        return zhgjsj;
    }

    public void setZhgjsj(Date zhgjsj) {
        this.zhgjsj = zhgjsj;
    }

    public String getKhlx() {
        return khlx;
    }

    public void setKhlx(String khlx) {
        this.khlx = khlx;
    }

    public Customer2Model() {
        super();
    }

    public Customer2Model(String uuid, String yuuid, String gsmc, String sf, String dq, String xsdz, String khlx, String khly,
                          String khdj, Date clsj, String zycp, String xsqy, String ygrs, String cs, String qylx,
                          String pp, String wz, BigDecimal ncke, BigDecimal ndde, String sydjh, String xsqd,
                          String cw_mc, String cw_nsrsbh, String cw_dz, String cw_dh, String cw_khh, String cw_zh,
                          String flag, String gsr, Timestamp system, long bh, Timestamp backsystimes, String xgr, Date zhgjsj) {
        this.uuid = uuid;
        this.yuuid = yuuid;
        this.gsmc = gsmc;
        this.sf = sf;
        this.dq = dq;
        this.xsdz = xsdz;
        this.khlx = khlx;
        this.khly = khly;
        this.khdj = khdj;
        this.clsj = clsj;
        this.zycp = zycp;
        this.xsqy = xsqy;
        this.ygrs = ygrs;
        this.cs = cs;
        this.qylx = qylx;
        this.pp = pp;
        this.wz = wz;
        this.ncke = ncke;
        this.ndde = ndde;
        this.sydjh = sydjh;
        this.xsqd = xsqd;
        this.cw_mc = cw_mc;
        this.cw_nsrsbh = cw_nsrsbh;
        this.cw_dz = cw_dz;
        this.cw_dh = cw_dh;
        this.cw_khh = cw_khh;
        this.cw_zh = cw_zh;
        this.flag = flag;
        this.gsr = gsr;
        this.system = system;
        this.bh = bh;
        this.backsystimes = backsystimes;
        this.xgr = xgr;
        this.zhgjsj = zhgjsj;
    }

    @Override
    public String toString() {
        return "Customer2Model{" +
                "uuid='" + uuid + '\'' +
                ", yuuid='" + yuuid + '\'' +
                ", gsmc='" + gsmc + '\'' +
                ", sf='" + sf + '\'' +
                ", dq='" + dq + '\'' +
                ", xsdz='" + xsdz + '\'' +
                ", khlxr='" + khlx + '\'' +
                ", khly='" + khly + '\'' +
                ", khdj='" + khdj + '\'' +
                ", clsj=" + clsj +
                ", zycp='" + zycp + '\'' +
                ", xsqy='" + xsqy + '\'' +
                ", ygrs='" + ygrs + '\'' +
                ", cs='" + cs + '\'' +
                ", qylx='" + qylx + '\'' +
                ", pp='" + pp + '\'' +
                ", wz='" + wz + '\'' +
                ", ncke=" + ncke +
                ", ndde=" + ndde +
                ", sydjh='" + sydjh + '\'' +
                ", xsqd='" + xsqd + '\'' +
                ", cw_mc='" + cw_mc + '\'' +
                ", cw_nsrsbh='" + cw_nsrsbh + '\'' +
                ", cw_dz='" + cw_dz + '\'' +
                ", cw_dh='" + cw_dh + '\'' +
                ", cw_khh='" + cw_khh + '\'' +
                ", cw_zh='" + cw_zh + '\'' +
                ", flag='" + flag + '\'' +
                ", gsr='" + gsr + '\'' +
                ", system=" + system +
                ", bh=" + bh +
                ", backsystimes=" + backsystimes +
                ", xgr=" + xgr +
                ", zhgjsj=" + zhgjsj +
                '}';
    }
}
