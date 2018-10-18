package com.hengbang.hbcrm.hb.accJur.mapper;

import com.hengbang.hbcrm.hb.accJur.model.AccJurModel;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
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
public interface AccJurMapper {

    String table = " acc_jur_table ";

    @Select({
            "select j.* from " + table + " a left join jurisdiction_table j on j.uuid = a.jur_id where a.acc_id = #{accid}"
    })
    List<JurisdictionModel> findByAccId(@Param("accid") String accid);

    @Select({
            "select * from " + table + " where acc_id = #{accid} and jur_id = #{jurid}"
    })
    List<AccJurModel> findByAccIdAndJurId(@Param("accid") String accid, @Param("jurid") String jurid);

    @Transactional
    @Delete({
            "delete from " + table + " where acc_id = #{accid} and jur_id = #{jurid}"
    })
    int deleteByAccIdAndJurId(@Param("accid") String accid, @Param("jurid") String jurid);

    @Transactional
    @Delete({
            "delete from " + table + " where acc_id = #{accid}"
    })
    int deleteByAccId(@Param("accid") String accid);

    @Transactional
    @Insert({
            "insert into " + table + " (uuid,acc_id,jur_id) values (#{model.uuid},#{model.accId},#{model.jurId})"
    })
    int add(@Param("model") AccJurModel model);
}
