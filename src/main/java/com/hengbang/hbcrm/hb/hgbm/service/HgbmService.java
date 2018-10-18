package com.hengbang.hbcrm.hb.hgbm.service;

import com.hengbang.hbcrm.hb.hgbm.model.HgbmModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

import java.util.List;

public interface HgbmService {

    ResponseResult<HgbmModel> add(HgbmModel model);

    ResponseResult<HgbmModel> deleteByUuid(String uuid);

    ResponseResult<HgbmModel> updateByUuid(HgbmModel model);

    ResponseResult<List<HgbmModel>> findByParents(String parents);
}
