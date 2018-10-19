package com.springboot.crm.business.dywh.mapper;

import com.springboot.crm.business.dywh.mapper.sql.DywhSql;
import com.springboot.crm.business.dywh.model.DywhModel;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface DywhMapper {

    String table = " dywh_table ";

    @Transactional
    @Insert({
            "insert into " + table + " (uuid,parents,lx,mc) values (#{model.uuid},#{model.parents},#{model.lx},#{model.mc})"
    })
    int add(@Param("model") DywhModel model);

    @Transactional
    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    int deleteById(@Param("uuid") String uuid);

    @Transactional
    @Delete({
            "delete from " + table + " where parents = #{parents}"
    })
    int deleteByParents(@Param("parents") String parents);

    @Transactional
    @UpdateProvider(type = DywhSql.class, method = "updateByIdSql")
    int updateById(@Param("model") DywhModel model);

    @Select({
            "select * from " + table + " where lx = #{lx}"
    })
    List<DywhModel> findByLx(@Param("lx") String lx);

    @Select({
            "select * from " + table + " where parents = #{parents}"
    })
    List<DywhModel> findByParents(@Param("parents") String parents);

    @Select({
            "select * from " + table + " where parents = #{parents} and mc = #{mc}"
    })
    List<DywhModel> findByParentsAndMc(@Param("parents") String parents, @Param("mc") String mc);

    @Select({
            "select * from " + table + " where uuid = #{uuid}"
    })
    DywhModel findByUuid(@Param("uuid") String uuid);
}
