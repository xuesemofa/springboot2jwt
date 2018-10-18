package com.hengbang.hbcrm.hb.bmgl.service;

import com.hengbang.hbcrm.hb.bmgl.model.BmglModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

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
