package com.hengbang.hbcrm.hb.account.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hengbang.hbcrm.hb.accJur.mapper.AccJurMapper;
import com.hengbang.hbcrm.hb.account.mapper.AccountMapper;
import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.hb.account.service.AccountService;
import com.hengbang.hbcrm.hb.bmgl.mapper.BmglMapper;
import com.hengbang.hbcrm.hb.customer.mapper.CustomerMapper;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
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
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper mapper;
    @Autowired
    private AccJurMapper accJurMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private BmglMapper bmglMapper;

    @Transactional
    @Override
    public ResponseResult<AccountModel> add(AccountModel model) {
        AccountModel model1 = mapper.getByAccount(model.getAccount());
        if (model1 != null)
            return new ResponseResult<>(false, "账户名称重复", null);
        else {
            model.setUuid(GetUuid.getUUID());
            mapper.add(model);
            return new ResponseResult<>(true, "成功", null);
        }
    }

    @Transactional
    @Override
    public ResponseResult<AccountModel> deleteById(String uuid) {
        AccountModel model = mapper.getByUuid(uuid);
        if (model == null)
            return new ResponseResult<>(false, "该账户已不存在");
        AccountModel model1 = mapper.getByUuid(model.getParents());
        if (model1 == null || model.getParents().equals("0"))
            customerMapper.updateByGsr(uuid, "0");
        else
            customerMapper.updateByGsr(uuid, model1.getUuid());
        List<AccountModel> list = mapper.findByParents(uuid);
        if (list.size() > 0)
            return new ResponseResult<>(false, "该账户存在下级，不能删除");
        mapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional
    @Override
    public ResponseResult<AccountModel> updateById(AccountModel model, boolean b) {
        int i = mapper.updateById(model);
        if (i > 0) {
            return new ResponseResult<>(true, "成功", null);
        } else
            return new ResponseResult<>(false, "失败", null);
    }

    @Transactional
    @Override
    public ResponseResult<AccountModel> updateByAccount(String account, String password) {
        mapper.updateByAccount(account, password);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<Page<AccountModel>> findAll(int pageNow, int pageSize, AccountModel model) {
        PageHelper.startPage(pageNow, pageSize);
        Page<AccountModel> page = mapper.findAll(model);
        if (page.size() > 0)
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Override
    public ResponseResult<AccountModel> getByUuid(String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            Subject subject = SecurityUtils.getSubject();
            AccountModel model1 = (AccountModel) subject.getPrincipal();
            uuid = model1.getUuid();
        }
        AccountModel model = mapper.getByUuid(uuid);
        if (model != null)
            return new ResponseResult<>(true, "成功", model);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Override
    public ResponseResult<AccountModel> getByAccount(String account) {
        AccountModel model = mapper.getByAccount(account);
        if (model != null)
            return new ResponseResult<>(true, "成功", model);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    @Override
    public ResponseResult<List<AccountModel>> findByParents(String parents) {
        if (parents.equals("-1")) {
            List<AccountModel> list = mapper.findByParents3();
            if (list.size() > 0)
                return new ResponseResult<>(true, "成功", list);
            else
                return new ResponseResult<>(false, "未查询到记录", null);
        } else {
            List<AccountModel> list = mapper.findByParents(parents);
            if (list.size() > 0)
                return new ResponseResult<>(true, "成功", list);
            else
                return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Override
    public ResponseResult<List<AccountModel>> findByParents2(String parents) {
        List<AccountModel> list = mapper.findByParents2(parents);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }
}
