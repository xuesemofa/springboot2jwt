package com.hengbang.hbcrm.hb.khdj.mapper;

import com.hengbang.hbcrm.hb.khdj.model.KhdjModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface KhdjMapper {

    String table = " khdj_table ";

    @Insert({
            "insert into " + table + " (uuid,mc) values (#{model.uuid},#{model.mc})"
    })
    int add(@Param("model") KhdjModel model);

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    int deleteById(@Param("uuid") String uuid);

    @Update({
            "update " + table + " set mc = #{model.mc} where uuid = #{model.uuid}"
    })
    int updateById(@Param("model") KhdjModel model);

    @Select({
            "select * from " + table + " where mc = #{mc}"
    })
    KhdjModel findByMc(@Param("mc") String mc);

    @Select({
            "select * from " + table
    })
    List<KhdjModel> findAll();
}
