package com.hengbang.hbcrm.hb.jurisdiction.service.impl;

import com.hengbang.hbcrm.hb.jurisdiction.mapper.JurisdictionMapper;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
import com.hengbang.hbcrm.hb.jurisdiction.service.JurisdictionService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
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
public class JurisdictionServiceImpl implements JurisdictionService {

    @Autowired
    private JurisdictionMapper mapper;

    @Override
    public ResponseResult<List<JurisdictionModel>> findAll() {
        List<JurisdictionModel> list = mapper.findAll();
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
