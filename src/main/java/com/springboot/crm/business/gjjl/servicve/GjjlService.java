package com.springboot.crm.business.gjjl.servicve;

import com.github.pagehelper.PageInfo;
import com.springboot.crm.business.gjjl.model.GjjlModel;
import com.springboot.crm.business.gjjl.model.GjjlQueryModel;
import com.springboot.crm.utils.result.ResponseResult;

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
