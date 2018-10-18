package com.hengbang.hbcrm.hb.gjpl.service;

import com.hengbang.hbcrm.hb.gjpl.model.GjplModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

public interface GjplService {

    ResponseResult<GjplModel> setGjpl(GjplModel model);

    ResponseResult<GjplModel> getByLx(int lx);
}
