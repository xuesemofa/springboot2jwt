package com.hengbang.hbcrm.hb.account.service;

import com.github.pagehelper.Page;
import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.utils.result.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AccountService {

    ResponseResult<AccountModel> add(AccountModel model);

    ResponseResult<AccountModel> deleteById(String uuid);

    ResponseResult<AccountModel> updateById(AccountModel model, boolean b);

    ResponseResult<AccountModel> updateByAccount(String account, String password);

    ResponseResult<Page<AccountModel>> findAll(int pageNow, int pageSize, AccountModel model);

    //    当参数未null时返回当前登陆人
    ResponseResult<AccountModel> getByUuid(String uuid);

    ResponseResult<AccountModel> getByAccount(String account);

    ResponseResult<List<AccountModel>> findByParents(String parents);

    ResponseResult<List<AccountModel>> findByParents2(String uuid);
}
