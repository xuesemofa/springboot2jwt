package com.springboot.crm.business.customer.mapper.sql;

import com.springboot.crm.business.customer.model.CusImgModel;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class CusImgSql {

    public String insertSql(@Param("list") List<CusImgModel> list, @Param("cusid") String cusid, @Param("accid") String accid) {
        StringJoiner sj = new StringJoiner("");
        list.forEach(k -> {
            String s = new SQL() {
                {
                    INSERT_INTO("cus_img_table");
                    VALUES("uuid", "'" + GetUuid.getUUID() + "'");
                    VALUES("cusid", "'" + cusid + "'");
                    VALUES("lx", "'1'");
                    VALUES("imgs", "'" + k.getImgs().substring(k.getImgs().lastIndexOf("/") + 1) + "'");
                    VALUES("scsj", "now()");
                    VALUES("accid", "'" + accid + "'");
                }
            }.toString();
            sj.add(s + ";");
        });
        String s = sj.toString().substring(0, sj.toString().length() - 1);
        return s;
    }
}
