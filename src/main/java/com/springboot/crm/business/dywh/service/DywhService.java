package com.springboot.crm.business.dywh.service;

import com.springboot.crm.business.dywh.model.DywhModel;
import com.springboot.crm.utils.result.ResponseResult;

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
