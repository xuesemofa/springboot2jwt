package com.springboot.crm.business.jurisdiction.service.impl;

import com.springboot.crm.business.jurisdiction.mapper.JurisdictionMapper;
import com.springboot.crm.business.jurisdiction.model.JurisdictionModel;
import com.springboot.crm.business.jurisdiction.service.JurisdictionService;
import com.springboot.crm.utils.result.ResponseResult;
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
