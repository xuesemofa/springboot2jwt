package com.springboot.crm.business.jurisdiction.mapper;

import com.springboot.crm.business.jurisdiction.model.JurisdictionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface JurisdictionMapper {

    String table = " jurisdiction_table ";

    @Select({
            "select * from " + table + " order by name"
    })
    List<JurisdictionModel> findAll();
}
