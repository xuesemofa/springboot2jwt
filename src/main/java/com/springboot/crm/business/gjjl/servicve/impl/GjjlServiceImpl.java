package com.springboot.crm.business.gjjl.servicve.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.crm.business.customer.mapper.CustomerMapper;
import com.springboot.crm.business.customer.model.CustomerModel;
import com.springboot.crm.business.gjjl.mapper.GjjlMapper;
import com.springboot.crm.business.gjjl.model.GjjlModel;
import com.springboot.crm.business.gjjl.model.GjjlQueryModel;
import com.springboot.crm.business.gjjl.servicve.GjjlService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class GjjlServiceImpl implements GjjlService {

    @Autowired
    private GjjlMapper mapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Transactional
    @Override
    public ResponseResult<GjjlModel> add(GjjlModel model) {
        CustomerModel model1 = new CustomerModel();
        model1.setUuid(model.getCusid());
        model1.setZhgjsj(model.getGjsj());
        customerMapper.updateById(model1);
        model.setUuid(GetUuid.getUUID());
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<PageInfo<GjjlModel>> findAll(int pageNow, int pageSize, GjjlQueryModel model) {
        PageHelper.startPage(pageNow, pageSize);
        Page<GjjlModel> page = mapper.findAll(model);
        PageInfo<GjjlModel> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<PageInfo<GjjlModel>> findAll2(int pageNow, int pageSize, GjjlQueryModel model) {
        PageHelper.startPage(pageNow, pageSize);
        Page<GjjlModel> page = mapper.findAll2(model);
        PageInfo<GjjlModel> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
