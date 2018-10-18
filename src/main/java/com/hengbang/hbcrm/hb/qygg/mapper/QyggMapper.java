package com.hengbang.hbcrm.hb.qygg.mapper;

import com.hengbang.hbcrm.hb.qygg.model.QyggModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QyggMapper {

    @Insert({
            "insert into qygg_table (uuid,titles,bodys,lx,systimes) " +
                    "values (#{model.uuid},#{model.titles},#{model.bodys},#{model.lx},#{model.systimes})"
    })
    int add(@Param("model") QyggModel model);

    @Select({
            "select q.*,max(q.systimes) from qygg_table q limit 1"
    })
    QyggModel findLack();
}
