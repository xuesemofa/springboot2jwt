package com.springboot.crm.business.qygg.service;

import com.springboot.crm.business.qygg.model.QyggModel;
import com.springboot.crm.utils.result.ResponseResult;

public interface QyggService {

    ResponseResult<QyggModel> add(QyggModel model);

    ResponseResult<QyggModel> findLack();
}
