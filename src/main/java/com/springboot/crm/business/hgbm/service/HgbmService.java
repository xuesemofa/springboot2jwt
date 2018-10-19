package com.springboot.crm.business.hgbm.service;

import com.springboot.crm.business.hgbm.model.HgbmModel;
import com.springboot.crm.utils.result.ResponseResult;

import java.util.List;

public interface HgbmService {

    ResponseResult<HgbmModel> add(HgbmModel model);

    ResponseResult<HgbmModel> deleteByUuid(String uuid);

    ResponseResult<HgbmModel> updateByUuid(HgbmModel model);

    ResponseResult<List<HgbmModel>> findByParents(String parents);
}
