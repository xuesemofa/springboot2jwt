package com.springboot.crm.business.customer.mapper;

import com.github.pagehelper.Page;
import com.springboot.crm.business.customer.mapper.sql.CustomerSql;
import com.springboot.crm.business.customer.model.Customer2Model;
import com.springboot.crm.business.customer.model.Customer3Model;
import com.springboot.crm.business.customer.model.CustomerModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface CustomerMapper {

    String table = " customer_table ";

    @Insert({
            "insert into " + table + " (uuid,gsmc,xsdz,khlx,khly," +
                    "khdj,clsj,zycp1,zycp2,zycp3,xsqy,ygrs," +
                    "cs,qylx,pp,wz,ncke," +
                    "ndde,sydjh,xsqd,cw_mc,cw_nsrsbh," +
                    "cw_dz,cw_dh,cw_khh,cw_zh,flag,gsr,system,sf,dq,bh,zhgjsj) " +
                    " values (#{model.uuid},#{model.gsmc},#{model.xsdz},#{model.khlx},#{model.khly}," +
                    "#{model.khdj},#{model.clsj},#{model.zycp1},#{model.zycp2},#{model.zycp3},#{model.xsqy},#{model.ygrs}," +
                    "#{model.cs},#{model.qylx},#{model.pp},#{model.wz},#{model.ncke}," +
                    "#{model.ndde},#{model.sydjh},#{model.xsqd},#{model.cw_mc},#{model.cw_nsrsbh}," +
                    "#{model.cw_dz},#{model.cw_dh},#{model.cw_khh},#{model.cw_zh},'0'" +
                    ",#{model.gsr},#{model.system},#{model.sf},#{model.dq},#{model.bh},#{model.zhgjsj})"
    })
    int add(@Param("model") CustomerModel model);

    //严禁使用此接口用作正常业务中，此接口为客户信息全部修改保留接口
    @Insert({
            " insert into customer_back_table (uuid,yuuid,gsmc,xsdz,khlx,khly,"
                    + " khdj,clsj,zycp1,zycp2,zycp3,xsqy,ygrs,"
                    + " cs,qylx,pp,wz,ncke,"
                    + " ndde,sydjh,xsqd,cw_mc,cw_nsrsbh,"
                    + " cw_dz,cw_dh,cw_khh,cw_zh,flag,gsr,system,sf,dq,bh,backsystimes,xgr,zhgjsj)"
                    + " select uuid() as uuid,c.uuid as yuuid,c.gsmc,c.xsdz,c.khlx,c.khly,"
                    + " c.khdj,c.clsj,c.zycp1,c.zycp2,c.zycp3,c.xsqy,c.ygrs,"
                    + " c.cs,c.qylx,c.pp,c.wz,c.ncke,"
                    + " c.ndde,c.sydjh,c.xsqd,c.cw_mc,c.cw_nsrsbh,"
                    + " c.cw_dz,c.cw_dh,c.cw_khh,c.cw_zh,c.flag,c.gsr,c.system,c.sf,c.dq,c.bh,now() as backsystimes,#{xgr},c.zhgjsj"
                    + " from customer_table c where c.uuid = #{uuid}"
    })
    int addBack(@Param("uuid") String uuid, @Param("xgr") String xgr);

    @Insert({
            " insert into customer_back3_table (uuid,yuuid,backsystimes,xgr,yz,xz)"
                    + " values (#{model.uuid},#{model.yuuid},#{model.backsystimes}," +
                    "#{model.xgr},#{model.yz},#{model.xz})"
    })
    int addBack3(@Param("model") Customer3Model model);

    @UpdateProvider(type = CustomerSql.class, method = "updateByIdSql")
    int updateById(@Param("model") CustomerModel model);

    @Update({
            "update " + table + " set gsr = #{gsr} where gsr = #{ygsr}"
    })
    int updateByGsr(@Param("ygsr") String ygsr,
                    @Param("gsr") String gsr);

    @Select({
            "select * from " + table + " where uuid = #{uuid}"
    })
    @Results({
            @Result(id = true, column = "uuid", property = "uuid"),
            @Result(property = "list", column = "uuid",
                    many = @Many(
                            select = "CusImgMapper.findByCusid")
            )
    })
    CustomerModel getById(@Param("uuid") String uuid);

    @Select({
            "SELECT c.uuid,c.gsmc,c.xsdz,c.khlx,c.khly,c.khdj,c.clsj,c.zycp1,c.zycp2,h.mc as zycp3,c.xsqy,c.ygrs,c.xsqd," +
                    "d1.mc as sf,d2.mc as dq,d3.mc as cs," +
                    "c.qylx,c.pp,c.wz,c.ncke,c.ndde,c.sydjh,c.cw_mc,c.cw_nsrsbh,c.cw_dz,c.cw_dh," +
                    "c.cw_khh,c.cw_zh,c.system,c.bh,c.gsr from customer_table c"
                    + " LEFT JOIN dywh_table d1 ON d1.uuid = c.sf"
                    + " LEFT JOIN dywh_table d2 ON d2.uuid = c.dq"
                    + " LEFT JOIN hgbm_table h ON h.uuid = c.zycp3"
                    + " LEFT JOIN dywh_table d3 ON d3.uuid = c.cs where c.uuid = #{cusid}"
    })
    @Results({
            @Result(property = "list", column = "uuid",
                    many = @Many(
                            select = "CusImgMapper.findByCusid")
            )
    })
    CustomerModel getById2(@Param("cusid") String cusid);

    @SelectProvider(type = CustomerSql.class, method = "findAllSql")
    Page<CustomerModel> findAll(@Param("model") CustomerModel model);

    @SelectProvider(type = CustomerSql.class, method = "findAllSql2")
    Page<CustomerModel> findAll2(@Param("model") CustomerModel model);

    //    全部客户 无论是谁的
    @SelectProvider(type = CustomerSql.class, method = "findAllSql3")
    Page<CustomerModel> findAll3(@Param("model") CustomerModel model);

    @SelectProvider(type = CustomerSql.class, method = "findAllSql4")
    Page<CustomerModel> findAll4(@Param("model") CustomerModel model,
                                 @Param("txsj") int txsj);

    @SelectProvider(type = CustomerSql.class, method = "findAllSql4Hz")
    Page<CustomerModel> findAll4Hz(@Param("model") CustomerModel model,
                                   @Param("txsj") int txsj);

    @Select({
            "select c.*,max(c.bh) from " + table + " c"
    })
    CustomerModel getMaxBh();

    //    修改记录
    @Select({
            "select c.uuid,a.name as xgr,c.backsystimes from customer_back_table c " +
                    "left join account_table a on a.uuid = c.xgr " +
                    "where c.yuuid = #{cusid} order by c.backsystimes desc"
    })
    Page<Customer2Model> findAllXgjl(@Param("cusid") String cusid);

    //    修改记录
    @Select({
            "select " +
                    "c.uuid,c.gsmc,dsf.mc as sf,ddq.mc as dq,dcs.mc as cs,c.xsdz,c.khlx,ly.mc as khly,dj.mc as khdj,c.gsr,c.bh" +
                    ",c.system,c.clsj,c.zycp1,c.zycp2,c.zycp3,c.xsqy,c.ygrs,c.xsqd,c.qylx,c.pp,c.wz,c.ncke,c.ndde,c.sydjh,c.cw_mc,c.cw_nsrsbh," +
                    "c.cw_dz,c.cw_dh,c.cw_khh,c.cw_zh" +
                    " from customer_back_table c left join dywh_table dsf on dsf.uuid = c.sf" +
                    " left join dywh_table ddq on ddq.uuid = c.dq" +
                    " left join dywh_table dcs on dcs.uuid = c.cs" +
                    " left join khly_table ly on ly.uuid = c.khly" +
                    " left join khdj_table dj on dj.uuid = c.khdj" +
                    " where c.uuid = #{uuid}"
    })
    Customer2Model findXgjlByUuid(@Param("uuid") String uuid);

    //    长时间未拜访的客户强制放入公海
    @Update(value = {
            " UPDATE customer_table"
                    + " SET gsr = '0'"
                    + " WHERE uuid = #{uuid}"
    })
    int updateByZhgjsj(@Param("uuid") String uuid);

    @Select({
            " select * from customer_table"
                    + " SET gsr = '0'"
                    + " WHERE"
                    + " datediff(now(), zhgjsj) > #{qzsj}"
                    + " AND gsr != '0'"
    })
    List<CustomerModel> findByZhgjsj(@Param("qzsj") int qzsj);

    //    根据条件查询指定人的非合作客户数量
    @Select({
            "select * from " + table + " where gsr = #{gsr} and khlx != '3' and flag = '0'"
    })
    List<CustomerModel> findByGsrAndKhlx(@Param("gsr") String gsr);

    //    根据名称查询
    @Select({
            "select * from " + table + " where gsmc = #{gsmc}"
    })
    List<CustomerModel> findByGsmc(@Param("gsmc") String gsmc);

    //    修改记录
    @Select({
            "select c.uuid,a.name as xgr,c.backsystimes,c.yz,c.xz from customer_back3_table c " +
                    "left join account_table a on a.uuid = c.xgr " +
                    "where c.yuuid = #{cusid} order by c.backsystimes desc"
    })
    Page<Customer3Model> findAllXgjl3(@Param("cusid") String cusid);

}
