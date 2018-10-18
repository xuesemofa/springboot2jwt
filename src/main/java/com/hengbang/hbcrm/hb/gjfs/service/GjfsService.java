package com.hengbang.hbcrm.hb.gjfs.service;

import com.hengbang.hbcrm.hb.gjfs.model.GjfsModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

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
