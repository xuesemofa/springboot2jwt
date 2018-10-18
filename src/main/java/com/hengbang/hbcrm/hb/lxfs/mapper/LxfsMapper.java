package com.hengbang.hbcrm.hb.lxfs.mapper;

import com.github.pagehelper.Page;
import com.hengbang.hbcrm.hb.lxfs.mapper.sql.LxfsSql;
import com.hengbang.hbcrm.hb.lxfs.model.LxfsModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface LxfsMapper {

    String table = " lxfs_table ";

    @Insert({
            "insert into " + table + " (uuid,xm,zw,dh,dzyj,alww,dd,hf,xg,xb,sr,cz,sjh,qq,qtlxfs,jtcy,zyrz,ssh,cusid,imgs)" +
                    " values (#{model.uuid},#{model.xm},#{model.zw},#{model.dh},#{model.dzyj},#{model.alww}," +
                    "#{model.dd},#{model.hf},#{model.xg},#{model.xb},#{model.sr},#{model.cz},#{model.sjh}," +
                    "#{model.qq},#{model.qtlxfs},#{model.jtcy},#{model.zyrz},#{model.ssh},#{model.cusid},#{model.imgs})"
    })
    int add(@Param("model") LxfsModel model);

    @UpdateProvider(type = LxfsSql.class, method = "updateById")
    int updateById(@Param("model") LxfsModel model);

    @Select({
            "select * from " + table + " where uuid = #{uuid}"
    })
    LxfsModel getById(@Param("uuid") String uuid);

    @SelectProvider(type = LxfsSql.class, method = "findAll")
    Page<LxfsModel> findAll(@Param("model") LxfsModel model);

    @SelectProvider(type = LxfsSql.class, method = "findAll")
    List<LxfsModel> findAll2(@Param("model") LxfsModel model);

}
