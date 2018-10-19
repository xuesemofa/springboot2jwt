package com.springboot.crm.business.gjpl.mapper;

import com.springboot.crm.business.gjpl.model.GjplModel;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GjplMapper {

    String table = " gjpl_table ";

    @Insert({
            "insert into " + table + " (uuid,lx,zq) values (#{model.uuid},#{model.lx},#{model.zq})"
    })
    int add(@Param("model") GjplModel model);

    @Update({
            "update " + table + " set zq = #{model.zq} where lx = #{model.lx}"
    })
    int update(@Param("model") GjplModel model);

    @Select({
            "select * from " + table + " where lx = #{lx}"
    })
    GjplModel getByLx(@Param("lx") int lx);

}
