package com.hengbang.hbcrm.hb.khdj.service.impl;

import com.hengbang.hbcrm.hb.khdj.mapper.KhdjMapper;
import com.hengbang.hbcrm.hb.khdj.model.KhdjModel;
import com.hengbang.hbcrm.hb.khdj.service.KhdjService;
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
public class KhdjServiceImpl implements KhdjService {

    @Autowired
    private KhdjMapper mapper;

    @Transactional
    @Override
    public ResponseResult<KhdjModel> add(KhdjModel model) {
        KhdjModel one = mapper.findByMc(model.getMc());
        if (one != null)
            return new ResponseResult<>(false, "名称重复");
        model.setUuid(GetUuid.getUUID());
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<KhdjModel> deleteById(String uuid) {
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<KhdjModel> updateById(KhdjModel model) {
        KhdjModel one = mapper.findByMc(model.getMc());
        if (one != null)
            return new ResponseResult<>(false, "名称重复");
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<KhdjModel>> findAll() {
        List<KhdjModel> list = mapper.findAll();
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
