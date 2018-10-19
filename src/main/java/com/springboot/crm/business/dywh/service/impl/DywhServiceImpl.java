package com.springboot.crm.business.dywh.service.impl;

import com.springboot.crm.business.dywh.mapper.DywhMapper;
import com.springboot.crm.business.dywh.model.DywhModel;
import com.springboot.crm.business.dywh.service.DywhService;
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
public class DywhServiceImpl implements DywhService {

    @Autowired
    private DywhMapper mapper;

    @Transactional
    @Override
    public ResponseResult<DywhModel> add(DywhModel model) {
        List<DywhModel> list = mapper.findByParentsAndMc(model.getParents(), model.getMc());
        if (list.size() > 0)
            return new ResponseResult<>(false, "名称重复", null);
        model.setUuid(GetUuid.getUUID());
        int i = mapper.add(model);
        if (i > 0)
            return new ResponseResult<>(true, "成功", null);
        else
            return new ResponseResult<>(false, "失败", null);
    }

    @Transactional
    @Override
    public ResponseResult<DywhModel> deleteById(String uuid) {
        int i = mapper.deleteById(uuid);
        if (i > 0)
            return new ResponseResult<>(true, "成功", null);
        else
            return new ResponseResult<>(false, "失败", null);
    }

    @Transactional
    @Override
    public ResponseResult<DywhModel> deleteByParents(String parents) {
        int i = mapper.deleteByParents(parents);
        if (i > 0)
            return new ResponseResult<>(true, "成功", null);
        else
            return new ResponseResult<>(false, "失败", null);
    }

    @Transactional
    @Override
    public ResponseResult<DywhModel> updateById(DywhModel model) {
        DywhModel model1 = mapper.findByUuid(model.getParents());
        if (model1 == null)
            model.setLx("1");
        else
            model.setLx(String.valueOf(Integer.parseInt(model1.getLx()) + 1));
        int i = mapper.updateById(model);
        if (i > 0)
            return new ResponseResult<>(true, "成功", null);
        else
            return new ResponseResult<>(false, "失败", null);
    }

    @Override
    public ResponseResult<List<DywhModel>> findByLx(String lx) {
        List<DywhModel> list = mapper.findByLx(lx);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Override
    public ResponseResult<List<DywhModel>> findByParents(String parents) {
        List<DywhModel> list = mapper.findByParents(parents);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }
}
