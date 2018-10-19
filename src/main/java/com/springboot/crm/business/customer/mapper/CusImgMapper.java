package com.springboot.crm.business.customer.mapper;

import com.springboot.crm.business.customer.mapper.sql.CusImgSql;
import com.springboot.crm.business.customer.model.CusImgModel;
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
public interface CusImgMapper {

    @Transactional
    @InsertProvider(type = CusImgSql.class, method = "insertSql")
    int adds(@Param("list") List<CusImgModel> list, @Param("cusid") String cusid, @Param("accid") String accid);

    @Select({
            "select * from cus_img_table where cusid = #{cusid}"
    })
    List<CusImgModel> findByCusid(@Param("cusid") String cusid);

    @Transactional
    @Delete({
            "delete from cus_img_table where cusid=#{cusid}"
    })
    void deleteByCusid(@Param("cusid") String cusid);
}
