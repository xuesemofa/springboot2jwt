package com.hengbang.hbcrm.hb.qygg.service;

import com.hengbang.hbcrm.hb.qygg.model.QyggModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

public interface QyggService {

    ResponseResult<QyggModel> add(QyggModel model);

    ResponseResult<QyggModel> findLack();
}
