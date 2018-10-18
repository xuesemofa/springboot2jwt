package com.hengbang.hbcrm.hb.customer.service;

import com.github.pagehelper.PageInfo;
import com.hengbang.hbcrm.hb.customer.model.YrghJlModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface YrghjlService {

    ResponseResult<YrghJlModel> add(YrghJlModel model);

    ResponseResult<PageInfo<YrghJlModel>> findAll(int pageNow, int pageSize, YrghJlModel model);
}
