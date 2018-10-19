package com.springboot.crm.business.txqk.service.impl;

import com.springboot.crm.business.txqk.mapper.TxQkMapper;
import com.springboot.crm.business.txqk.model.TxqkModel;
import com.springboot.crm.business.txqk.service.TxqkService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
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
