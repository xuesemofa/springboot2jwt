package com.springboot.crm.business.customer.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.crm.business.customer.mapper.YrghjlMapper;
import com.springboot.crm.business.customer.model.YrghJlModel;
import com.springboot.crm.business.customer.service.YrghjlService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class YrghjlServiceImpl implements YrghjlService {

    @Autowired
    private YrghjlMapper mapper;

    @Override
    public ResponseResult<YrghJlModel> add(YrghJlModel model) {
        model.setUuid(GetUuid.getUUID());
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<PageInfo<YrghJlModel>> findAll(int pageNow, int pageSize, YrghJlModel model) {
        PageHelper.startPage(pageNow, pageSize);
        Page<YrghJlModel> page = mapper.findAll(model);
        PageInfo<YrghJlModel> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
