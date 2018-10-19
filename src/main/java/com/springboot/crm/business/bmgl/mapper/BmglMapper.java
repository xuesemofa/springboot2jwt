package com.springboot.crm.business.bmgl.mapper;

import com.springboot.crm.business.bmgl.model.BmglModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface BmglMapper {

    String table = " bmgl_table ";

    @Insert({
            "insert into " + table + " (uuid,parents,mc,bla,blb) " +
                    " values (#{model.uuid},#{model.parents},#{model.mc},#{model.bla},#{model.blb})"
    })
    int add(@Param("model") BmglModel model);

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    int deleteById(@Param("uuid") String uuid);

    @Update({
            "update " + table + " set mc = #{model.mc} where uuid = #{model.uuid}"
    })
    int updateById(@Param("model") BmglModel model);

    @Select({
            "select * from " + table + " where parents = #{parents}"
    })
    List<BmglModel> findByParents(@Param("parents") String parents);

    @Select({
            "select * from " + table + " where parents = #{parents} and mc = #{mc}"
    })
    List<BmglModel> findByParentsAndMc(@Param("parents") String parents, @Param("mc") String mc);

    @Select({
            "select * from " + table + " where uuid = #{uuid}"
    })
    BmglModel findByUuid(@Param("uuid") String uuid);

    @Select({
            "select * from " + table
    })
    List<BmglModel> findAll();
}
