package com.springboot.crm.business.khslxz.service.impl;

import com.springboot.crm.business.khslxz.mapper.KhslxzMapper;
import com.springboot.crm.business.khslxz.model.KhslxzModel;
import com.springboot.crm.business.khslxz.service.KhslxzService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhslxzServiceImpl implements KhslxzService {

    @Autowired
    private KhslxzMapper mapper;

    @Override
    public ResponseResult<KhslxzModel> setSl(KhslxzModel model) {
        List<KhslxzModel> list = mapper.findAll();
        if (list.size() > 0) {
            int i = 0;
            for (KhslxzModel m : list) {
                if (m.getLx() != null && m.getLx().equals(model.getLx()))
                    i = 1;
            }
            if (i > 0)
                mapper.update(model);
            else
                mapper.add(model);
        } else {
            model.setUuid(GetUuid.getUUID());
            mapper.add(model);
        }
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<KhslxzModel>> findAll() {
        List<KhslxzModel> list = mapper.findAll();
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据");
    }
}
