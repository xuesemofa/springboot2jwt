package com.springboot.crm.business.bmgl.service;

import com.springboot.crm.business.bmgl.model.BmglModel;
import com.springboot.crm.utils.result.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface BmglService {

    ResponseResult<BmglModel> add(BmglModel model);

    ResponseResult<BmglModel> deleteById(String uuid);

    ResponseResult<BmglModel> updateById(BmglModel model);

    ResponseResult<List<BmglModel>> findByParents(String parents);

    ResponseResult<BmglModel> findByUuid(String uuid);

    ResponseResult<List<BmglModel>> findAll();
}
