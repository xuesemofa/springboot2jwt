package com.springboot.crm.business.gjpl.service;

import com.springboot.crm.business.gjpl.model.GjplModel;
import com.springboot.crm.utils.result.ResponseResult;

public interface GjplService {

    ResponseResult<GjplModel> setGjpl(GjplModel model);

    ResponseResult<GjplModel> getByLx(int lx);
}
