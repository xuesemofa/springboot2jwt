package com.springboot.crm.business.khly.service;

import com.springboot.crm.business.khly.model.KhlyModel;
import com.springboot.crm.utils.result.ResponseResult;

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
