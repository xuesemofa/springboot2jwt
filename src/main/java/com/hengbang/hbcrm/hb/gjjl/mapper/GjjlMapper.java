package com.hengbang.hbcrm.hb.gjjl.mapper;

import com.github.pagehelper.Page;
import com.hengbang.hbcrm.hb.gjjl.mapper.sql.GjjlSql;
import com.hengbang.hbcrm.hb.gjjl.model.GjjlModel;
import com.hengbang.hbcrm.hb.gjjl.model.GjjlQueryModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface GjjlMapper {

    String table = " gjjl_table ";

    @Insert({
            "insert into " + table + " (uuid,cusid,gjsj,gjr,gjfs,lxr,gjqk,ldsy)" +
                    " values (#{model.uuid},#{model.cusid},#{model.gjsj},#{model.gjr},#{model.gjfs},#{model.lxr}," +
                    "#{model.gjqk},#{model.ldsy})"
    })
    int add(@Param("model") GjjlModel model);

    @SelectProvider(type = GjjlSql.class, method = "findAllSql")
    Page<GjjlModel> findAll(@Param("model") GjjlQueryModel model);

    @SelectProvider(type = GjjlSql.class, method = "findAllSql2")
    Page<GjjlModel> findAll2(@Param("model") GjjlQueryModel model);
}
