package com.springboot.crm.business.gjjl.mapper.sql;

import com.springboot.crm.business.account.model.AccountModel;
import com.springboot.crm.business.gjjl.model.GjjlQueryModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class GjjlSql {

    public String findAllSql(@Param("model") GjjlQueryModel model) {
        return new SQL() {
            {
                SELECT("g.uuid,c.gsmc as cusid,g.gjsj,a.name as gjr,fs.mc as gjfs,lx.xm as lxr,g.gjqk");
                FROM("gjjl_table g");
                LEFT_OUTER_JOIN("account_table a on a.uuid = g.gjr");
                LEFT_OUTER_JOIN("gjfs_table fs on fs.uuid = g.gjfs");
                LEFT_OUTER_JOIN("lxfs_table lx on lx.uuid = g.lxr");
                LEFT_OUTER_JOIN("customer_table c on c.uuid = g.cusid");
                if (model.getCusid() != null && !model.getCusid().isEmpty())
                    WHERE("g.cusid=#{model.cusid}");
                if (model.getGjr() != null && !model.getGjr().isEmpty())
                    WHERE("g.gjr=#{model.gjr}");
                if (model.getGjfs() != null && !model.getGjfs().isEmpty())
                    WHERE("g.gjfs=#{model.gjfs}");
                if (model.getGjsjStr() != null)
                    WHERE("date_format(g.gjsj,'%Y-%m-%d') >= date_format(#{model.gjsjStr},'%Y-%m-%d')");
                if (model.getGjsjEnd() != null)
                    WHERE("date_format(g.gjsj,'%Y-%m-%d') <= date_format(#{model.gjsjEnd},'%Y-%m-%d')");

                ORDER_BY("g.gjsj desc");
            }
        }.toString();
    }

    public String findAllSql2(@Param("model") GjjlQueryModel model) {
        Subject subject = SecurityUtils.getSubject();
        AccountModel model1 = (AccountModel) subject.getPrincipal();
        return new SQL() {
            {
                SELECT("g.uuid,c.gsmc as cusid,g.gjsj,a.name as gjr,fs.mc as gjfs,lx.xm as lxr,g.gjqk");
                FROM("gjjl_table g");
                LEFT_OUTER_JOIN("account_table a on a.uuid = g.gjr");
                LEFT_OUTER_JOIN("gjfs_table fs on fs.uuid = g.gjfs");
                LEFT_OUTER_JOIN("lxfs_table lx on lx.uuid = g.lxr");
                LEFT_OUTER_JOIN("customer_table c on c.uuid = g.cusid");
                if (model.getCusid() != null && !model.getCusid().isEmpty())
                    WHERE("g.cusid=#{model.cusid}");
                if (model.getGjr() != null && !model.getGjr().isEmpty())
                    WHERE("g.gjr=#{model.gjr}");
                else
                    WHERE("(g.gjr in (select a1.uuid from account_table a1 where a1.parents = '"
                            + model1.getUuid()
                            + "') or g.gjr = '" + model1.getUuid() + "')");
                if (model.getGjfs() != null && !model.getGjfs().isEmpty())
                    WHERE("g.gjfs=#{model.gjfs}");
                if (model.getGjsjStr() != null)
                    WHERE("date_format(g.gjsj,'%Y-%m-%d') >= date_format(#{model.gjsjStr},'%Y-%m-%d')");
                if (model.getGjsjEnd() != null)
                    WHERE("date_format(g.gjsj,'%Y-%m-%d') <= date_format(#{model.gjsjEnd},'%Y-%m-%d')");

                ORDER_BY("g.gjsj desc");
            }
        }.toString();
    }
}
