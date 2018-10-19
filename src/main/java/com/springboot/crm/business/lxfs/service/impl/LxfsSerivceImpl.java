package com.springboot.crm.business.lxfs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.crm.business.account.model.AccountModel;
import com.springboot.crm.business.customer.mapper.CustomerMapper;
import com.springboot.crm.business.customer.model.CustomerModel;
import com.springboot.crm.business.lxfs.mapper.LxfsMapper;
import com.springboot.crm.business.lxfs.model.LxfsModel;
import com.springboot.crm.business.lxfs.service.LxfsService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
public class LxfsSerivceImpl implements LxfsService {

    @Autowired
    private LxfsMapper mapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Transactional
    @Override
    public ResponseResult<LxfsModel> add(LxfsModel model) {
        CustomerModel one = customerMapper.getById(model.getCusid());
        if (one != null) {
            Subject subject = SecurityUtils.getSubject();
            AccountModel model2 = (AccountModel) subject.getPrincipal();
            boolean b = model2.getUuid().equals(one.getGsr());
            if (!b)
                return new ResponseResult<>(false, "当前客户已不属于当前登陆人");
        } else
            return new ResponseResult<>(false, "未查询到当前客户");
        model.setUuid(GetUuid.getUUID());
        mapper.add(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<LxfsModel> updateById(LxfsModel model) {
        CustomerModel one = customerMapper.getById(model.getCusid());
        if (one != null) {
            Subject subject = SecurityUtils.getSubject();
            AccountModel model2 = (AccountModel) subject.getPrincipal();
            boolean b = model2.getUuid().equals(one.getGsr());
            if (!b)
                return new ResponseResult<>(false, "当前客户已不属于当前登陆人");
        } else
            return new ResponseResult<>(false, "未查询到当前客户");
        mapper.updateById(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<LxfsModel> getById(String uuid) {
        LxfsModel one = mapper.getById(uuid);
        if (one != null)
            return new ResponseResult<>(true, "成功", one);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<PageInfo<LxfsModel>> findAll(int pageNow, int pageSize, LxfsModel model) {
        PageHelper.startPage(pageNow, pageSize);
        Page<LxfsModel> page = mapper.findAll(model);
        PageInfo<LxfsModel> pageInfo = new PageInfo<>(page);
        if (pageInfo.getTotal() > 0)
            return new ResponseResult<>(true, "成功", pageInfo);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<List<LxfsModel>> findAll2(LxfsModel model) {
        List<LxfsModel> list = mapper.findAll2(model);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
