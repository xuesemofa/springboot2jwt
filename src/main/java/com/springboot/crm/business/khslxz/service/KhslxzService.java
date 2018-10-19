package com.springboot.crm.business.khslxz.service;

import com.springboot.crm.business.khslxz.model.KhslxzModel;
import com.springboot.crm.utils.result.ResponseResult;

import java.util.List;

public interface KhslxzService {

    ResponseResult<KhslxzModel> setSl(KhslxzModel model);

    ResponseResult<List<KhslxzModel>> findAll();
}
