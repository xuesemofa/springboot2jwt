package com.hengbang.hbcrm.hb.lxfs.service;

import com.github.pagehelper.PageInfo;
import com.hengbang.hbcrm.hb.lxfs.model.LxfsModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

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
