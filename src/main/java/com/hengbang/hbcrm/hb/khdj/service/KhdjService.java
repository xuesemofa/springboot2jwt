package com.hengbang.hbcrm.hb.khdj.service;

import com.hengbang.hbcrm.hb.khdj.model.KhdjModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

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
