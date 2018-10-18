package com.hengbang.hbcrm.hb.txqk.service.impl;

import com.hengbang.hbcrm.hb.txqk.mapper.TxQkMapper;
import com.hengbang.hbcrm.hb.txqk.model.TxqkModel;
import com.hengbang.hbcrm.hb.txqk.service.TxqkService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class TxqkServiceImpl implements TxqkService {

    @Autowired
    private TxQkMapper mapper;

    @Override
    public ResponseResult<TxqkModel> setTxqkSj(TxqkModel model) {
        List<TxqkModel> list = mapper.findAll();
        if (list.size() > 0)
            mapper.update(model);
        else {
            model.setUuid(GetUuid.getUUID());
            mapper.add(model);
        }
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<TxqkModel> findAll() {
        List<TxqkModel> list = mapper.findAll();
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list.get(0));
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
