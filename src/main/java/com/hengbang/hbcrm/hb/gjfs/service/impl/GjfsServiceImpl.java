package com.hengbang.hbcrm.hb.gjfs.service.impl;

import com.hengbang.hbcrm.hb.gjfs.mapper.GjfsMapper;
import com.hengbang.hbcrm.hb.gjfs.model.GjfsModel;
import com.hengbang.hbcrm.hb.gjfs.service.GjfsService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
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
public class GjfsServiceImpl implements GjfsService {

    @Autowired
    private GjfsMapper mapper;

    @Transactional
    @Override
    public ResponseResult<GjfsModel> add(GjfsModel model) {
        GjfsModel one = mapper.findByMc(model.getMc());
        if (one != null)
            return new ResponseResult<>(false, "名称重复");
        model.setUuid(GetUuid.getUUID());
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<GjfsModel> deleteById(String uuid) {
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<GjfsModel> updateById(GjfsModel model) {
        GjfsModel one = mapper.findByMc(model.getMc());
        if (one != null)
            return new ResponseResult<>(false, "名称重复");
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<GjfsModel>> findAll() {
        List<GjfsModel> list = mapper.findAll();
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
