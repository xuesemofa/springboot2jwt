package com.hengbang.hbcrm.hb.gjjl.servicve;

import com.github.pagehelper.PageInfo;
import com.hengbang.hbcrm.hb.gjjl.model.GjjlModel;
import com.hengbang.hbcrm.hb.gjjl.model.GjjlQueryModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface GjjlService {

    ResponseResult<GjjlModel> add(GjjlModel model);

    ResponseResult<PageInfo<GjjlModel>> findAll(int pageNow, int pageSize, GjjlQueryModel model);

    ResponseResult<PageInfo<GjjlModel>> findAll2(int pageNow, int pageSize, GjjlQueryModel model);
}
