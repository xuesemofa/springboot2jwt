package com.hengbang.hbcrm.hb.khly.mapper;

import com.hengbang.hbcrm.hb.khly.model.KhlyModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface KhlyMapper {

    String table = " khly_table ";

    @Insert({
            "insert into " + table + " (uuid,mc) values (#{model.uuid},#{model.mc})"
    })
    int add(@Param("model") KhlyModel model);

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    int deleteById(@Param("uuid") String uuid);

    @Update({
            "update " + table + " set mc = #{model.mc} where uuid = #{model.uuid}"
    })
    int updateById(@Param("model") KhlyModel model);

    @Select({
            "select * from " + table + " where mc = #{mc}"
    })
    KhlyModel findByMc(@Param("mc") String mc);

    @Select({
            "select * from " + table
    })
    List<KhlyModel> findAll();
}
