package com.hengbang.hbcrm.hb.dywh.service;

import com.hengbang.hbcrm.hb.dywh.model.DywhModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface DywhService {

    ResponseResult<DywhModel> add(DywhModel model);

    ResponseResult<DywhModel> deleteById(String uuid);

    ResponseResult<DywhModel> deleteByParents(String parents);

    ResponseResult<DywhModel> updateById(DywhModel model);

    ResponseResult<List<DywhModel>> findByLx(String lx);

    ResponseResult<List<DywhModel>> findByParents(String parents);
}
