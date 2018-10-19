package com.springboot.crm.business.customer.mapper;

import com.github.pagehelper.Page;
import com.springboot.crm.business.customer.mapper.sql.YrghjlSql;
import com.springboot.crm.business.customer.model.YrghJlModel;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface YrghjlMapper {

    String table = " yrghjl_table ";

    @Transactional
    @Insert({
            "insert into " + table + " (uuid,czr,ygsr,systimes," +
                    "ddhgsr,bdddkh,ddyy) " +
                    " values (#{model.uuid},#{model.czr},#{model.ygsr},#{model.systimes}," +
                    "#{model.ddhgsr},#{model.bdddkh},#{model.ddyy})"
    })
    int add(@Param("model") YrghJlModel model);

    @SelectProvider(type = YrghjlSql.class, method = "findAllBySQL")
    Page<YrghJlModel> findAll(@Param("model") YrghJlModel model);

    @Select({
            "select y.uuid,y.czr,y.ygsr,max(y.systimes) as systimes from " + table + " y " +
                    "where y.bdddkh = #{bdddkh} and y.ygsr = #{ygsr}"
    })
    YrghJlModel getByBdddkh(@Param("bdddkh") String bdddkh, @Param("ygsr") String ygsr);


}
