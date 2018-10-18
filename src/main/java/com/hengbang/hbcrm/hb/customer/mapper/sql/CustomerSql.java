package com.hengbang.hbcrm.hb.customer.mapper.sql;

import com.hengbang.hbcrm.hb.customer.model.CustomerModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.math.BigDecimal;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class CustomerSql {

    public String updateByIdSql(@Param("model") CustomerModel model) {
        return new SQL() {
            {
                UPDATE("customer_table");
//                if (model.getGsmc() != null && !model.getGsmc().isEmpty())
//                    SET("gsmc=#{model.gsmc}");
                if (model.getSf() != null && !model.getSf().isEmpty())
                    SET("sf=#{model.sf}");
                if (model.getDq() != null && !model.getDq().isEmpty())
                    SET("dq=#{model.dq}");
                if (model.getXsdz() != null && !model.getXsdz().isEmpty())
                    SET("xsdz=#{model.xsdz}");
                if (model.getKhlx() != null && !model.getKhlx().isEmpty())
                    SET("khlx=#{model.khlx}");
                if (model.getKhly() != null && !model.getKhly().isEmpty())
                    SET("khly=#{model.khly}");
                if (model.getKhdj() != null && !model.getKhdj().isEmpty())
                    SET("khdj=#{model.khdj}");
                if (model.getClsj() != null)
                    SET("clsj=#{model.clsj}");
                if (model.getZycp1() != null && !model.getZycp1().isEmpty())
                    SET("zycp1=#{model.zycp1}");
                if (model.getZycp2() != null && !model.getZycp2().isEmpty())
                    SET("zycp2=#{model.zycp2}");
                if (model.getZycp3() != null && !model.getZycp3().isEmpty())
                    SET("zycp3=#{model.zycp3}");
                if (model.getXsqy() != null && !model.getXsqy().isEmpty())
                    SET("xsqy=#{model.xsqy}");
                if (model.getYgrs() != null && !model.getYgrs().isEmpty())
                    SET("ygrs=#{model.ygrs}");
                if (model.getCs() != null && !model.getCs().isEmpty())
                    SET("cs=#{model.cs}");
                if (model.getQylx() != null && !model.getQylx().isEmpty())
                    SET("qylx=#{model.qylx}");
                if (model.getPp() != null && !model.getPp().isEmpty())
                    SET("pp=#{model.pp}");
                if (model.getWz() != null && !model.getWz().isEmpty())
                    SET("wz=#{model.wz}");
                if (model.getNcke() != null && model.getNcke().compareTo(new BigDecimal("0")) == 1)
                    SET("ncke=#{model.ncke}");
                if (model.getNdde() != null && model.getNdde().compareTo(new BigDecimal("0")) == 1)
                    SET("ndde=#{model.ndde}");
                if (model.getSydjh() != null && !model.getSydjh().isEmpty())
                    SET("sydjh=#{model.sydjh}");
                if (model.getXsqd() != null && !model.getXsqd().isEmpty())
                    SET("xsqd=#{model.xsqd}");
                if (model.getCw_mc() != null && !model.getCw_mc().isEmpty())
                    SET("cw_mc=#{model.cw_mc}");
                if (model.getCw_nsrsbh() != null && !model.getCw_nsrsbh().isEmpty())
                    SET("cw_nsrsbh=#{model.cw_nsrsbh}");
                if (model.getCw_dz() != null && !model.getCw_dz().isEmpty())
                    SET("cw_dz=#{model.cw_dz}");
                if (model.getCw_dh() != null && !model.getCw_dh().isEmpty())
                    SET("cw_dh=#{model.cw_dh}");
                if (model.getCw_khh() != null && !model.getCw_khh().isEmpty())
                    SET("cw_khh=#{model.cw_khh}");
                if (model.getCw_zh() != null && !model.getCw_zh().isEmpty())
                    SET("cw_zh=#{model.cw_zh}");
//                if (model.getBh() != null && !model.getBh().isEmpty())
//                    SET("bh=#{model.bh}");
                if (model.getGsr() != null && !model.getGsr().isEmpty())
                    SET("gsr=#{model.gsr}");
                if (model.getZhgjsj() != null)
                    SET("zhgjsj=#{model.zhgjsj}");
                if (model.getUuid() != null && !model.getUuid().isEmpty())
                    WHERE("uuid = #{model.uuid}");
                WHERE("flag = '0'");
            }
        }.toString();
    }

    public String findAllSql(@Param("model") CustomerModel model) {
        return new SQL() {
            {
                SELECT("c.uuid,c.gsmc,dsf.mc as sf,ddq.mc as dq,dcs.mc as cs,c.xsdz,ly.mc as khly,c.khlx,c.khdj," +
                        "c.gsr,c.bh,c.zhgjsj");
                FROM("customer_table c left join dywh_table dsf on dsf.uuid = c.sf" +
                        " left join dywh_table ddq on ddq.uuid = c.dq" +
                        " left join dywh_table dcs on dcs.uuid = c.cs" +
                        " left join khly_table ly on ly.uuid = c.khly");
                if (model.getGsmc() != null && !model.getGsmc().isEmpty()) {
                    model.setGsmc("%" + model.getGsmc() + "%");
                    WHERE("c.gsmc like #{model.gsmc}");
                }
                if (model.getXsdz() != null && !model.getXsdz().isEmpty()) {
                    model.setXsdz("%" + model.getXsdz() + "%");
                    WHERE("c.xsdz like #{model.xsdz}");
                }
                if (model.getBh() > 0)
                    WHERE("bh=#{model.bh}");
                if (model.getKhlx() != null && !model.getKhlx().isEmpty())
                    WHERE("khlx=#{model.khlx}");
                if (model.getKhly() != null && !model.getKhly().isEmpty())
                    WHERE("khly=#{model.khly}");
                if (model.getKhdj() != null && !model.getKhdj().isEmpty())
                    WHERE("khdj=#{model.khdj}");
                if (model.getSf() != null && !model.getSf().isEmpty())
                    WHERE("sf=#{model.sf}");
                if (model.getDq() != null && !model.getDq().isEmpty())
                    WHERE("dq=#{model.dq}");
                if (model.getCs() != null && !model.getCs().isEmpty())
                    WHERE("cs=#{model.cs}");
                if (model.getQylx() != null && !model.getQylx().isEmpty())
                    WHERE("qylx=#{model.qylx}");
                if (model.getGsr() != null && !model.getGsr().isEmpty()) {
                    if (model.getGsr().equals("0"))
                        WHERE("c.gsr='0' or c.gsr = '' or c.gsr is null");
                    else
                        WHERE("c.gsr=#{model.gsr}");
                }
                WHERE("c.flag = '0'");
            }
        }.toString();
    }

    public String findAllSql2(@Param("model") CustomerModel model) {
        return new SQL() {
            {
                SELECT("c.uuid,c.gsmc,dsf.mc as sf,ddq.mc as dq,dcs.mc as cs,c.xsdz,c.khly,c.khdj,a2.name as gsr,c.bh,c.zhgjsj");
                FROM("customer_table c left join dywh_table dsf on dsf.uuid = c.sf" +
                        " left join dywh_table ddq on ddq.uuid = c.dq" +
                        " left join dywh_table dcs on dcs.uuid = c.cs" +
                        " left join account_table a2 on a2.uuid = c.gsr");
                if (model.getGsmc() != null && !model.getGsmc().isEmpty()) {
                    model.setGsmc("%" + model.getGsmc() + "%");
                    WHERE("c.gsmc like #{model.gsmc}");
                }
                if (model.getXsdz() != null && !model.getXsdz().isEmpty()) {
                    model.setXsdz("%" + model.getXsdz() + "%");
                    WHERE("c.xsdz like #{model.xsdz}");
                }
                if (model.getBh() > 0)
                    WHERE("bh=#{model.bh}");
                if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                    WHERE("c.uuid=#{model.uuid}");
                }
                if (model.getKhlx() != null && !model.getKhlx().isEmpty())
                    WHERE("khlx=#{model.khlx}");
                if (model.getKhly() != null && !model.getKhly().isEmpty())
                    WHERE("khly=#{model.khly}");
                if (model.getKhdj() != null && !model.getKhdj().isEmpty())
                    WHERE("khdj=#{model.khdj}");
                if (model.getSf() != null && !model.getSf().isEmpty())
                    WHERE("sf=#{model.sf}");
                if (model.getDq() != null && !model.getDq().isEmpty())
                    WHERE("dq=#{model.dq}");
                if (model.getCs() != null && !model.getCs().isEmpty())
                    WHERE("cs=#{model.cs}");
                if (model.getQylx() != null && !model.getQylx().isEmpty())
                    WHERE("qylx=#{model.qylx}");
                WHERE("(c.gsr in (SELECT uuid from account_table where parents = #{model.gsr})" +
                        " or c.gsr = #{model.gsr})");
                WHERE("c.flag = '0'");
            }
        }.toString();
    }

    public String findAllSql3(@Param("model") CustomerModel model) {
        return new SQL() {
            {
                SELECT("c.uuid,c.gsmc,c.system,dsf.mc as sf,ddq.mc as dq,dcs.mc as cs,c.xsdz,ly.mc as khly,dj.mc as khdj," +
                        "case c.gsr when '0' then '0' else a.name end as gsr,c.bh");
                FROM("customer_table c");
                LEFT_OUTER_JOIN("account_table a on a.uuid = c.gsr");
                LEFT_OUTER_JOIN("dywh_table dsf on dsf.uuid = c.sf");
                LEFT_OUTER_JOIN("dywh_table ddq on ddq.uuid = c.dq");
                LEFT_OUTER_JOIN("dywh_table dcs on dcs.uuid = c.cs");
                LEFT_OUTER_JOIN("khly_table ly on ly.uuid = c.khly");
                LEFT_OUTER_JOIN("khdj_table dj on dj.uuid = c.khdj");
                WHERE("c.flag = '0'");
                if (model.getGsmc() != null && !model.getGsmc().isEmpty()) {
                    model.setGsmc("%" + model.getGsmc() + "%");
                    WHERE("c.gsmc like #{model.gsmc}");
                }
                if (model.getXsdz() != null && !model.getXsdz().isEmpty()) {
                    model.setXsdz("%" + model.getXsdz() + "%");
                    WHERE("c.xsdz like #{model.xsdz}");
                }
                if (model.getBh() > 0)
                    WHERE("bh=#{model.bh}");
                if (model.getGsr() != null && !model.getGsr().isEmpty()) {
                    if (model.getGsr().equals("0"))
                        WHERE("c.gsr='0' or c.gsr = '' or c.gsr is null");
                    else
                        WHERE("c.gsr=#{model.gsr}");
                }
                if (model.getKhly() != null && !model.getKhly().isEmpty())
                    WHERE("khly=#{model.khly}");
                if (model.getKhdj() != null && !model.getKhdj().isEmpty())
                    WHERE("khdj=#{model.khdj}");
                if (model.getSf() != null && !model.getSf().isEmpty())
                    WHERE("sf=#{model.sf}");
                if (model.getDq() != null && !model.getDq().isEmpty())
                    WHERE("dq=#{model.dq}");
                if (model.getCs() != null && !model.getCs().isEmpty())
                    WHERE("cs=#{model.cs}");
                if (model.getQylx() != null && !model.getQylx().isEmpty())
                    WHERE("qylx=#{model.qylx}");
            }
        }.toString();
    }

    public String findAllSql4(@Param("model") CustomerModel model, @Param("txsj") int txsj) {
        return new SQL() {
            {
                SELECT("datediff(now(),c.zhgjsj) as flag,c.uuid,c.gsmc,dsf.mc as sf,ddq.mc as dq,dcs.mc as cs,c.xsdz,ly.mc as khly,dj.mc as khdj,c.gsr,c.bh");
                FROM("customer_table c left join dywh_table dsf on dsf.uuid = c.sf" +
                        " left join dywh_table ddq on ddq.uuid = c.dq" +
                        " left join dywh_table dcs on dcs.uuid = c.cs" +
                        " left join khly_table ly on ly.uuid = c.khly" +
                        " left join khdj_table dj on dj.uuid = c.khdj");
                if (model.getGsmc() != null && !model.getGsmc().isEmpty()) {
                    model.setGsmc("%" + model.getGsmc() + "%");
                    WHERE("c.gsmc like #{model.gsmc}");
                }
                if (model.getXsdz() != null && !model.getXsdz().isEmpty()) {
                    model.setXsdz("%" + model.getXsdz() + "%");
                    WHERE("c.xsdz like #{model.xsdz}");
                }
                if (model.getBh() > 0)
                    WHERE("bh=#{model.bh}");
                if (model.getGsr() != null && !model.getGsr().isEmpty()) {
                    if (model.getGsr().equals("0"))
                        WHERE("c.gsr='0' or c.gsr = '' or c.gsr is null");
                    else
                        WHERE("c.gsr=#{model.gsr}");
                }
                if (model.getKhly() != null && !model.getKhly().isEmpty())
                    WHERE("khly=#{model.khly}");
                if (model.getKhdj() != null && !model.getKhdj().isEmpty())
                    WHERE("khdj=#{model.khdj}");
                if (model.getSf() != null && !model.getSf().isEmpty())
                    WHERE("sf=#{model.sf}");
                if (model.getDq() != null && !model.getDq().isEmpty())
                    WHERE("dq=#{model.dq}");
                if (model.getCs() != null && !model.getCs().isEmpty())
                    WHERE("cs=#{model.cs}");
                if (model.getQylx() != null && !model.getQylx().isEmpty())
                    WHERE("qylx=#{model.qylx}");
                WHERE("datediff(now(),c.zhgjsj) > #{txsj}");
                WHERE("c.flag = '0'");
                WHERE("c.khlx != '3'");
                ORDER_BY("flag");
            }
        }.toString();
    }

    public String findAllSql4Hz(@Param("model") CustomerModel model, @Param("txsj") int txsj) {
        return new SQL() {
            {
                SELECT("datediff(now(),c.zhgjsj) as flag,c.uuid,c.gsmc,dsf.mc as sf,ddq.mc as dq,dcs.mc as cs,c.xsdz,ly.mc as khly,dj.mc as khdj,c.gsr,c.bh");
                FROM("customer_table c left join dywh_table dsf on dsf.uuid = c.sf" +
                        " left join dywh_table ddq on ddq.uuid = c.dq" +
                        " left join dywh_table dcs on dcs.uuid = c.cs" +
                        " left join khly_table ly on ly.uuid = c.khly" +
                        " left join khdj_table dj on dj.uuid = c.khdj");
                if (model.getGsmc() != null && !model.getGsmc().isEmpty()) {
                    model.setGsmc("%" + model.getGsmc() + "%");
                    WHERE("c.gsmc like #{model.gsmc}");
                }
                if (model.getXsdz() != null && !model.getXsdz().isEmpty()) {
                    model.setXsdz("%" + model.getXsdz() + "%");
                    WHERE("c.xsdz like #{model.xsdz}");
                }
                if (model.getBh() > 0)
                    WHERE("bh=#{model.bh}");
                if (model.getGsr() != null && !model.getGsr().isEmpty()) {
                    if (model.getGsr().equals("0"))
                        WHERE("c.gsr='0' or c.gsr = '' or c.gsr is null");
                    else
                        WHERE("c.gsr=#{model.gsr}");
                }
                if (model.getKhly() != null && !model.getKhly().isEmpty())
                    WHERE("khly=#{model.khly}");
                if (model.getKhdj() != null && !model.getKhdj().isEmpty())
                    WHERE("khdj=#{model.khdj}");
                if (model.getSf() != null && !model.getSf().isEmpty())
                    WHERE("sf=#{model.sf}");
                if (model.getDq() != null && !model.getDq().isEmpty())
                    WHERE("dq=#{model.dq}");
                if (model.getCs() != null && !model.getCs().isEmpty())
                    WHERE("cs=#{model.cs}");
                if (model.getQylx() != null && !model.getQylx().isEmpty())
                    WHERE("qylx=#{model.qylx}");
                WHERE("datediff(now(),c.zhgjsj) > #{txsj}");
                WHERE("c.flag = '0'");
                WHERE("c.khlx = '3'");
                ORDER_BY("flag");
            }
        }.toString();
    }
}
