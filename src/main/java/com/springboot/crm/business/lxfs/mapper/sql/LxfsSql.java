package com.springboot.crm.business.lxfs.mapper.sql;

import com.springboot.crm.business.lxfs.model.LxfsModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class LxfsSql {

    public String updateById(@Param("model") LxfsModel model) {
        return new SQL() {
            {
//                uuid,xm,zw,dh,dzyj,alww,dd,hf,xg,xb,sr,cz,sjh,qq,qtlxfs,jtcy,zyrz,ssh,cusid
                UPDATE("lxfs_table");
//                if (model.getXm() != null && !model.getXm().isEmpty())
//                    SET("xm=#{model.xm}");
                if (model.getZw() != null && !model.getZw().isEmpty())
                    SET("zw=#{model.zw}");
                if (model.getDh() != null && !model.getDh().isEmpty())
                    SET("dh=#{model.dh}");
                if (model.getDzyj() != null && !model.getDzyj().isEmpty())
                    SET("dzyj=#{model.dzyj}");
                if (model.getAlww() != null && !model.getAlww().isEmpty())
                    SET("alww = #{model.alww}");
                if (model.getDd() != null && !model.getDd().isEmpty())
                    SET("dd = #{model.dd}");
                if (model.getHf() != null && !model.getHf().isEmpty())
                    SET("hf = #{model.hf}");
                if (model.getXg() != null && !model.getXg().isEmpty())
                    SET("xg = #{model.xg}");
                if (model.getXb() != null && !model.getXb().isEmpty())
                    SET("xb = #{model.xb}");
                if (model.getSr() != null)
                    SET("sr = #{model.sr}");
                if (model.getCz() != null && !model.getCz().isEmpty())
                    SET("cz = #{model.cz}");
                if (model.getSjh() != null && !model.getSjh().isEmpty())
                    SET("sjh = #{model.sjh}");
                if (model.getQq() != null && !model.getQq().isEmpty())
                    SET("qq = #{model.qq}");
                if (model.getQtlxfs() != null && !model.getQtlxfs().isEmpty())
                    SET("qtlxfs = #{model.qtlxfs}");
                if (model.getJtcy() != null && !model.getJtcy().isEmpty())
                    SET("jtcy = #{model.jtcy}");
                if (model.getZyrz() != null)
                    SET("zyrz = #{model.zyrz}");
                if (model.getSsh() > 0)
                    SET("ssh = #{model.ssh}");
                if (model.getImgs() != null && !model.getImgs().isEmpty())
                    SET("imgs = #{model.imgs}");

                if (model.getUuid() != null && !model.getUuid().isEmpty())
                    WHERE("uuid = #{model.uuid}");
            }
        }.toString();
    }

    public String findAll(@Param("model") LxfsModel model) {
        return new SQL() {
            {
                SELECT("*");
                FROM("lxfs_table");
                if (model.getCusid() != null && !model.getCusid().isEmpty())
                    WHERE("cusid = #{model.cusid}");
            }
        }.toString();
    }
}
