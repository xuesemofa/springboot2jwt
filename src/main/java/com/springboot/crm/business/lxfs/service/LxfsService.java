package com.springboot.crm.business.lxfs.service;

import com.github.pagehelper.PageInfo;
import com.springboot.crm.business.lxfs.model.LxfsModel;
import com.springboot.crm.utils.result.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface LxfsService {

    ResponseResult<LxfsModel> add(LxfsModel model);

    ResponseResult<LxfsModel> updateById(LxfsModel model);

    ResponseResult<LxfsModel> getById(String uuid);

    ResponseResult<PageInfo<LxfsModel>> findAll(int pageNow, int pageSize, LxfsModel model);

    ResponseResult<List<LxfsModel>> findAll2(LxfsModel model);
}
