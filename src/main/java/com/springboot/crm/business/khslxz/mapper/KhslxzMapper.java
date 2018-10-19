package com.springboot.crm.business.khslxz.mapper;

import com.springboot.crm.business.khslxz.model.KhslxzModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KhslxzMapper {

    String table = " khslxz_table ";

    @Insert({
            "insert into " + table + " (uuid,lx,sl) " +
                    " values (#{model.uuid},#{model.lx},#{model.sl})"
    })
    int add(@Param("model") KhslxzModel model);

    @Update({
            "update " + table + " set sl = #{model.sl} where lx = #{model.lx}"
    })
    int update(@Param("model") KhslxzModel model);

    @Select({
            "select * from " + table
    })
    List<KhslxzModel> findAll();
}
