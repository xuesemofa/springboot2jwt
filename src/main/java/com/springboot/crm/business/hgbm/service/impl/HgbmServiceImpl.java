package com.springboot.crm.business.hgbm.service.impl;

import com.springboot.crm.business.hgbm.mapper.HgbmMapper;
import com.springboot.crm.business.hgbm.model.HgbmModel;
import com.springboot.crm.business.hgbm.service.HgbmService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Slf4j
@Service
public class HgbmServiceImpl implements HgbmService {

    @Autowired
    private HgbmMapper mapper;

    @Transient
    @Override
    public ResponseResult<HgbmModel> add(HgbmModel model) {
        List<HgbmModel> list = mapper.findByMc(model.getMc());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复");
        model.setUuid(GetUuid.getUUID());
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transient
    @Override
    public ResponseResult<HgbmModel> deleteByUuid(String uuid) {
        mapper.deleteByUuid(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transient
    @Override
    public ResponseResult<HgbmModel> updateByUuid(HgbmModel model) {
        mapper.updateByUuid(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<HgbmModel>> findByParents(String parents) {
        List<HgbmModel> list = mapper.findByParents(parents);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据");
    }
}
