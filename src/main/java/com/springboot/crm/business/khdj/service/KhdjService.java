package com.springboot.crm.business.khdj.service;

import com.springboot.crm.business.khdj.model.KhdjModel;
import com.springboot.crm.utils.result.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface KhdjService {

    ResponseResult<KhdjModel> add(KhdjModel model);

    ResponseResult<KhdjModel> deleteById(String uuid);

    ResponseResult<KhdjModel> updateById(KhdjModel model);

    ResponseResult<List<KhdjModel>> findAll();
}
