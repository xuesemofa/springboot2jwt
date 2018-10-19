package com.springboot.crm.business.gjpl.service.impl;

import com.springboot.crm.business.gjpl.mapper.GjplMapper;
import com.springboot.crm.business.gjpl.model.GjplModel;
import com.springboot.crm.business.gjpl.service.GjplService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GjplServiceImpl implements GjplService {

    @Autowired
    private GjplMapper mapper;

    @Transactional
    @Override
    public ResponseResult<GjplModel> setGjpl(GjplModel model) {
        GjplModel one = mapper.getByLx(model.getLx());
        if (one != null)
            mapper.update(model);
        else {
            model.setUuid(GetUuid.getUUID());
            mapper.add(model);
        }
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<GjplModel> getByLx(int lx) {
        GjplModel one = mapper.getByLx(lx);
        if (one != null)
            return new ResponseResult<>(true, "成功", one);
        return new ResponseResult<>(false, "未查询到记录");
    }
}
