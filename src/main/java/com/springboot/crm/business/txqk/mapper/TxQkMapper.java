package com.springboot.crm.business.txqk.mapper;

import com.springboot.crm.business.txqk.model.TxqkModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Mapper
public interface TxQkMapper {

    String table = " txqk_table ";

    @Insert({
            "insert into " + table + " (uuid,txsj,qzyrghsj)" +
                    " values (#{model.uuid},#{model.txsj},#{model.qzyrghsj})"
    })
    int add(@Param("model") TxqkModel model);

    @Update({
            "update " + table + " set txsj = #{model.txsj},qzyrghsj = #{model.qzyrghsj}"
    })
    int update(@Param("model") TxqkModel model);

    @Select({
            "select * from " + table
    })
    List<TxqkModel> findAll();
}
