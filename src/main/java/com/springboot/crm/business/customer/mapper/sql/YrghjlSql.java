package com.springboot.crm.business.customer.mapper.sql;

import com.springboot.crm.business.customer.model.YrghJlModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class YrghjlSql {

    public String findAllBySQL(@Param("model") YrghJlModel model) {
        return new SQL() {
            {
                SELECT("y.uuid,case y.czr when '0' then '系统' else a.account end AS czr,a1.account as ygsr,y.ddhgsr,y.ddyy,y.systimes");
                FROM("yrghjl_table y");
                LEFT_OUTER_JOIN("account_table a on a.uuid = y.czr");
                LEFT_OUTER_JOIN("account_table a1 on a1.uuid = y.ygsr");
                if (model.getBdddkh() != null && !model.getBdddkh().isEmpty())
                    WHERE("y.bdddkh = (#{model.bdddkh})");
                if (model.getCzr() != null && !model.getCzr().isEmpty())
                    WHERE("y.czr = (#{model.czr})");
                if (model.getUuid() != null && !model.getUuid().isEmpty())
                    WHERE("y.uuid = #{model.uuid}");
            }
        }.toString();
    }
}
