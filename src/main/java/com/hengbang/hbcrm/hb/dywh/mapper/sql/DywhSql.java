package com.hengbang.hbcrm.hb.dywh.mapper.sql;

import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.hb.dywh.model.DywhModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class DywhSql {

    public String updateByIdSql(@Param("model") DywhModel model) {
        return new SQL() {
            {
                UPDATE("dywh_table");
                if (model.getParents() != null && !model.getParents().isEmpty())
                    SET("parents=#{model.parents}");
                if (model.getLx() != null && !model.getLx().isEmpty())
                    SET("lx=#{model.lx}");
                if (model.getMc() != null && !model.getMc().isEmpty())
                    SET("mc=#{model.mc}");
                if (model.getUuid() != null && !model.getUuid().isEmpty())
                    WHERE("uuid = #{model.uuid}");
            }
        }.toString();
    }

    public String findAllSql(@Param("model") AccountModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM("account_table");
                if (model.getAccount() != null && !model.getAccount().isEmpty()) {
                    model.setAccount("%" + model.getAccount() + "%");
                    WHERE("account like #{model.account}");
                }
                if (model.getParents() != null && !model.getParents().isEmpty())
                    WHERE("parents=#{model.parents}");
                if (model.getName() != null && !model.getName().isEmpty()) {
                    model.setName("%" + model.getName() + "%");
                    WHERE("name like #{model.name}");
                }
                if (model.getPhone() != null && !model.getPhone().isEmpty())
                    WHERE("phone=#{model.phone}");
            }
        }.toString();
    }
}
