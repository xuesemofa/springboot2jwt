package com.springboot.crm.business.bmgl.service.impl;

import com.springboot.crm.business.bmgl.mapper.BmglMapper;
import com.springboot.crm.business.bmgl.model.BmglModel;
import com.springboot.crm.business.bmgl.service.BmglService;
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
public class BmglServiceImpl implements BmglService {

    @Autowired
    private BmglMapper mapper;

    @Transactional
    @Override
    public ResponseResult<BmglModel> add(BmglModel model) {
        List<BmglModel> list = mapper.findByParentsAndMc(model.getParents(), model.getMc());
        if (list.size() > 0)
            return new ResponseResult<>(false, "已存在");
        else {
            model.setUuid(GetUuid.getUUID());
            mapper.add(model);
            return new ResponseResult<>(true, "成功");
        }
    }

    @Transactional
    @Override
    public ResponseResult<BmglModel> deleteById(String uuid) {
        List<BmglModel> list = mapper.findByParents(uuid);
        if (list.size() > 0)
            return new ResponseResult<>(false, "请先删除下级");
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<BmglModel> updateById(BmglModel model) {
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<BmglModel>> findByParents(String parents) {
        List<BmglModel> list = mapper.findByParents(parents);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<BmglModel> findByUuid(String uuid) {
        BmglModel model = mapper.findByUuid(uuid);
        if (model != null)
            return new ResponseResult<>(true, "成功", model);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<List<BmglModel>> findAll() {
        List<BmglModel> list = mapper.findAll();
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
