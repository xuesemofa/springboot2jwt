package com.springboot.crm.business.gjfs.mapper;

import com.springboot.crm.business.gjfs.model.GjfsModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface GjfsMapper {

    String table = " gjfs_table ";

    @Insert({
            "insert into " + table + " (uuid,mc) values (#{model.uuid},#{model.mc})"
    })
    int add(@Param("model") GjfsModel model);

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    int deleteById(@Param("uuid") String uuid);

    @Update({
            "update " + table + " set mc = #{model.mc} where uuid = #{model.uuid}"
    })
    int updateById(@Param("model") GjfsModel model);

    @Select({
            "select * from " + table + " where mc = #{mc}"
    })
    GjfsModel findByMc(@Param("mc") String mc);

    @Select({
            "select * from " + table
    })
    List<GjfsModel> findAll();
}
