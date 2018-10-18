package com.hengbang.hbcrm.hb.khly.service;

import com.hengbang.hbcrm.hb.khly.model.KhlyModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface KhlyService {

    ResponseResult<KhlyModel> add(KhlyModel model);

    ResponseResult<KhlyModel> deleteById(String uuid);

    ResponseResult<KhlyModel> updateById(KhlyModel model);

    ResponseResult<List<KhlyModel>> findAll();
}
