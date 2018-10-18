package com.hengbang.hbcrm.hb.qygg.service.impl;

import com.hengbang.hbcrm.hb.qygg.mapper.QyggMapper;
import com.hengbang.hbcrm.hb.qygg.model.QyggModel;
import com.hengbang.hbcrm.hb.qygg.service.QyggService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QyggServiceImpl implements QyggService {

    @Autowired
    private QyggMapper mapper;

    @Transactional
    @Override
    public ResponseResult<QyggModel> add(QyggModel model) {
        model.setUuid(GetUuid.getUUID());
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<QyggModel> findLack() {
        QyggModel model = mapper.findLack();
        if (model != null)
            return new ResponseResult<>(true, "成功", model);
        return new ResponseResult<>(false, "未查询到记录");
    }
}
