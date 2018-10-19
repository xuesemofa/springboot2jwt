package com.springboot.crm.business.qygg.service.impl;

import com.springboot.crm.business.qygg.mapper.QyggMapper;
import com.springboot.crm.business.qygg.model.QyggModel;
import com.springboot.crm.business.qygg.service.QyggService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
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
