package com.springboot.crm.business.gjfs.service;

import com.springboot.crm.business.gjfs.model.GjfsModel;
import com.springboot.crm.utils.result.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface GjfsService {

    ResponseResult<GjfsModel> add(GjfsModel model);

    ResponseResult<GjfsModel> deleteById(String uuid);

    ResponseResult<GjfsModel> updateById(GjfsModel model);

    ResponseResult<List<GjfsModel>> findAll();
}
