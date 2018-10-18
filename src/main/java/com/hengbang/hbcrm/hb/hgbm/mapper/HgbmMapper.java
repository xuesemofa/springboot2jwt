package com.hengbang.hbcrm.hb.hgbm.mapper;

import com.hengbang.hbcrm.hb.hgbm.model.HgbmModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HgbmMapper {

    String table = " hgbm_table ";

    @Insert({
            "insert into " + table + "(uuid,parents,mc) values (#{model.uuid},#{model.parents},#{model.mc})"
    })
    int add(@Param("model") HgbmModel model);

    @Delete({
            "delete " + table + " where uuid = #{uuid}"
    })
    int deleteByUuid(@Param("uuid") String uuid);

    @Update({
            "update " + table + " set mc = #{model.mc} where uuid = #{model.uuid}"
    })
    int updateByUuid(@Param("model") HgbmModel model);

    @Select({
            "select * from " + table + " where parents = #{parents} order by orders"
    })
    List<HgbmModel> findByParents(@Param("parents") String parents);

    @Select({
            "select * from " + table + " where mc = #{mc}"
    })
    List<HgbmModel> findByMc(@Param("mc") String mc);
}
