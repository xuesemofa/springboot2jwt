package com.springboot.crm.business.customer.service;

import com.github.pagehelper.PageInfo;
import com.springboot.crm.business.customer.model.YrghJlModel;
import com.springboot.crm.utils.result.ResponseResult;

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
