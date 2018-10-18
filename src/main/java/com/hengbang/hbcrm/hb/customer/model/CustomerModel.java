package com.hengbang.hbcrm.hb.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ld
 * @name 冲突条件为公司名称和电话
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerModel implements Serializable {

    private String uuid;

    //    公司名称
    @NotBlank(message = "公司名称不能为空")
    @Size(max = 200, message = "公司名称最大长度为200位")
    private String gsmc;
    //    省份
    @NotBlank(message = "省份不能为空")
    private String sf;
    //    地区
    @NotBlank(message = "市不能为空")
    private String dq;
    //    详细地址
    @NotBlank(message = "详细地址不能为空")
    @Size(max = 200, message = "详细地址最大长度为200位")
    private String xsdz;
    //    客户来源
//    @NotBlank(message = "客户来源不能为空")
    @Size(max = 200, message = "客户来源最大长度为200位")
    private String khly;
    //    客户类型 1:未签约 2：断约 3：合作
    @NotBlank(message = "客户类型不能为空")
    private String khlx;
    //    客户等级
    @NotBlank(message = "客户等级不能为空")
    private String khdj;
    //    成立时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date clsj = null;
    //    主营产品
//    @Size(max = 200, message = "主营产品最大长度为200位")
    private String zycp1;
    private String zycp2;
    private String zycp3;
    //    销售区域
    @Size(max = 200, message = "销售区域最大长度为200位")
    private String xsqy;
    //    员工人数
    @Size(max = 200, message = "员工人数最大长度为200位")
    private String ygrs;
    //    城市
    @NotBlank(message = "区县不能为空")
    @Size(max = 200, message = "区县最大长度为200位")
    private String cs;
    //    企业类型
    @Size(max = 200, message = "企业类型最大长度为200位")
    private String qylx;
    //    品牌
    @Size(max = 200, message = "品牌最大长度为200位")
    private String pp;
    //    网站
    @Size(max = 200, message = "网站最大长度为200位")
    private String wz;
    //    年出口额
    @Min(value = 0, message = "年出口额最小为0")
    private BigDecimal ncke = new BigDecimal(0);
    //    年订单额
    @Min(value = 0, message = "年订单额最小为0")
    private BigDecimal ndde = new BigDecimal(0);
    //    商业登记号
    @Size(max = 200, message = "商业登记号最大长度为200位")
    private String sydjh;
    //    销售渠道
    @Size(max = 200, message = "销售渠道最大长度为200位")
    private String xsqd;
    //    财务-名称
    @Size(max = 200, message = "财务-名称最大长度为200位")
    private String cw_mc;
    //    纳税人识别号
    @Size(max = 200, message = "纳税人识别号最大长度为200位")
    private String cw_nsrsbh;
    //    财务-地址
    @Size(max = 200, message = "财务-地址最大长度为200位")
    private String cw_dz;
    //    财务-电话
    @Size(max = 200, message = "财务-电话最大长度为200位")
    private String cw_dh;
    //    财务-开户行
    @Size(max = 200, message = "财务-开户行最大长度为200位")
    private String cw_khh;
    //    财务-账号
    @Size(max = 200, message = "财务-账号最大长度为200位")
    private String cw_zh;
    //    0：正常数据 -1：删除数据
    private String flag = "0";

    //    归属人 公海的为0
    private String gsr;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp system;
    //编号
    private long bh;

    //    最后一次跟进时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date zhgjsj;

    //    图片 一对多
    private List<CusImgModel> list = new ArrayList<>();

    //    排序字段
    private String fields;
    //    排序类型
    private String orderBys;
    private boolean px;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGsmc() {
        return gsmc;
    }

    public void setGsmc(String gsmc) {
        this.gsmc = gsmc;
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

    public long getBh() {
        return bh;
    }

    public void setBh(long bh) {
        this.bh = bh;
    }

    public Date getZhgjsj() {
        return zhgjsj;
    }

    public void setZhgjsj(Date zhgjsj) {
        this.zhgjsj = zhgjsj;
    }

    public List<CusImgModel> getList() {
        return list;
    }

    public void setList(List<CusImgModel> list) {
        this.list = list;
    }

    public String getZycp1() {
        return zycp1;
    }

    public void setZycp1(String zycp1) {
        this.zycp1 = zycp1;
    }

    public String getZycp2() {
        return zycp2;
    }

    public void setZycp2(String zycp2) {
        this.zycp2 = zycp2;
    }

    public String getZycp3() {
        return zycp3;
    }

    public void setZycp3(String zycp3) {
        this.zycp3 = zycp3;
    }

    public String getKhlx() {
        return khlx;
    }

    public void setKhlx(String khlx) {
        this.khlx = khlx;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getOrderBys() {
        return orderBys;
    }

    public void setOrderBys(String orderBys) {
        if (orderBys != null)
            if (orderBys.equals("1"))
                orderBys = "desc";
            else
                orderBys = "";
        else
            orderBys = "";
        this.orderBys = orderBys;
    }

    public boolean isPx() {
        if (this.fields == null || this.fields.isEmpty())
            return false;
        return true;
    }

    public CustomerModel() {
        super();
    }

    public CustomerModel(String uuid,
                         String gsmc,
                         String sf,
                         String dq,
                         String xsdz,
                         String khlx,
                         String khly,
                         String khdj,
                         Date clsj,
                         String zycp1,
                         String zycp2,
                         String zycp3,
                         String xsqy,
                         String ygrs,
                         String cs,
                         String qylx,
                         String pp,
                         String wz,
                         BigDecimal ncke,
                         BigDecimal ndde,
                         String sydjh,
                         String xsqd,
                         String cw_mc,
                         String cw_nsrsbh,
                         String cw_dz,
                         String cw_dh,
                         String cw_khh,
                         String cw_zh,
                         String flag,
                         String gsr,
                         Timestamp system,
                         long bh,
                         Date zhgjsj,
                         List<CusImgModel> list,
                         String fields,
                         String orderBys) {
        this.uuid = uuid;
        this.gsmc = gsmc;
        this.sf = sf;
        this.dq = dq;
        this.xsdz = xsdz;
        this.khlx = khlx;
        this.khly = khly;
        this.khdj = khdj;
        this.clsj = clsj;
        this.zycp1 = zycp1;
        this.zycp2 = zycp2;
        this.zycp3 = zycp3;
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
        this.zhgjsj = zhgjsj;
        this.list = list;
        this.fields = fields;
        this.orderBys = orderBys;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "uuid='" + uuid + '\'' +
                ", gsmc='" + gsmc + '\'' +
                ", sf='" + sf + '\'' +
                ", dq='" + dq + '\'' +
                ", xsdz='" + xsdz + '\'' +
                ", khlx='" + khlx + '\'' +
                ", khly='" + khly + '\'' +
                ", khdj='" + khdj + '\'' +
                ", clsj=" + clsj +
                ", zycp1=" + zycp1 +
                ", zycp2=" + zycp2 +
                ", zycp3=" + zycp3 +
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
                ", system=" + system + '\'' +
                ", bh=" + bh + '\'' +
                ", zhgjsj=" + zhgjsj + '\'' +
                ", list=" + list + '\'' +
                ", fields=" + fields + '\'' +
                ", orderBys=" + orderBys +
                '}';
    }

}
