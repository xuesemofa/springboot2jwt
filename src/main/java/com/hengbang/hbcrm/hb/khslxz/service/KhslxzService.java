package com.hengbang.hbcrm.hb.khslxz.service;

import com.hengbang.hbcrm.hb.khslxz.model.KhslxzModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

import java.util.List;

public interface KhslxzService {

    ResponseResult<KhslxzModel> setSl(KhslxzModel model);

    ResponseResult<List<KhslxzModel>> findAll();
}
