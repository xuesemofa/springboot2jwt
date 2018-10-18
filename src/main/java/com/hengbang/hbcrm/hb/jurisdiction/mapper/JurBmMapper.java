package com.hengbang.hbcrm.hb.jurisdiction.mapper;

import com.hengbang.hbcrm.hb.jurisdiction.model.JurBmModel;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface JurBmMapper {

    String table = " jur_bm_table";

    @Insert({
            "insert into " + table + " (uuid,jurId,bmId) " +
                    " values (#{model.uuid},#{model.jurId},#{model.bmId})"
    })
    int add(@Param("model") JurBmModel model);

    @Delete({
            "delete from " + table + " where uuid = #{uuid}"
    })
    int deleteById(@Param("uuid") String uuid);

    @Delete({
            "delete from " + table + " where bmId = #{bmid} and jurId = #{jurid}"
    })
    int deleteByBmIdAndJurId(@Param("bmid") String bmid, @Param("jurid") String jurid);

    @Select({
            "select * from " + table + " where bmId = #{bmid}"
    })
    List<JurBmModel> findByBmId(@Param("bmid") String bmid);

    @Select({
            "select jur.* from " + table + " j " +
                    "left join jurisdiction_table jur " +
                    "on jur.uuid = j.jurid " +
                    "where bmId = #{bmid} and jur.uuid != ''"
    })
    List<JurisdictionModel> findByBmId2(@Param("bmid") String bmid);

    @Select({
            "select * from " + table + " where bmId = #{bmid} and jurId = #{jurid}"
    })
    List<JurBmModel> findByBmIdAndJurId(@Param("bmid") String bmid, @Param("jurid") String jurid);
}
