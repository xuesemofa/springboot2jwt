package com.springboot.crm.business.khly.service.impl;

import com.springboot.crm.business.khly.mapper.KhlyMapper;
import com.springboot.crm.business.khly.model.KhlyModel;
import com.springboot.crm.business.khly.service.KhlyService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class KhlyServiceImpl implements KhlyService {

    @Autowired
    private KhlyMapper mapper;

    @Transactional
    @Override
    public ResponseResult<KhlyModel> add(KhlyModel model) {
        KhlyModel one = mapper.findByMc(model.getMc());
        if (one != null)
            return new ResponseResult<>(false, "名称重复");
        model.setUuid(GetUuid.getUUID());
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<KhlyModel> deleteById(String uuid) {
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<KhlyModel> updateById(KhlyModel model) {
        KhlyModel one = mapper.findByMc(model.getMc());
        if (one != null)
            return new ResponseResult<>(false, "名称重复");
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<KhlyModel>> findAll() {
        List<KhlyModel> list = mapper.findAll();
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
