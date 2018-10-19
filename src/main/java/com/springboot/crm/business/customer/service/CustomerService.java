package com.springboot.crm.business.customer.service;

import com.github.pagehelper.PageInfo;
import com.springboot.crm.business.customer.model.Customer2Model;
import com.springboot.crm.business.customer.model.Customer3Model;
import com.springboot.crm.business.customer.model.CustomerModel;
import com.springboot.crm.utils.result.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface CustomerService {

    ResponseResult<CustomerModel> add(CustomerModel model, HttpServletRequest request);

    ResponseResult<CustomerModel> updateGsrById(CustomerModel model, String bz, HttpServletRequest request);

    ResponseResult<CustomerModel> updateGsrById2(CustomerModel model);

    ResponseResult<CustomerModel> updateGsrById3(CustomerModel model);

    ResponseResult<CustomerModel> updateGsrById4(CustomerModel model, HttpServletRequest request);

    ResponseResult<CustomerModel> getById(String uuid);

    ResponseResult<CustomerModel> getById2(String uuid);

    ResponseResult<CustomerModel> getById3(String uuid);

    ResponseResult<PageInfo<CustomerModel>> findAll(int pageNow, int pageSize, CustomerModel model);

    ResponseResult<PageInfo<CustomerModel>> findAll2(int pageNow, int pageSize, CustomerModel model);

    ResponseResult<PageInfo<CustomerModel>> findAll3(int pageNow, int pageSize, CustomerModel model);

    //    长时间未拜访的客户
    ResponseResult<PageInfo<CustomerModel>> findAll4(int pageNow, int pageSize, CustomerModel model);

    ResponseResult<PageInfo<CustomerModel>> findAll4Hz(int pageNow, int pageSize, CustomerModel model);

    //更改归属人
    ResponseResult<CustomerModel> gggsr(CustomerModel model);

    //    修改记录
    ResponseResult<PageInfo<Customer2Model>> xgjl(int pageNow, int pageSize, String cusid);

    //    查看修改记录详情
    ResponseResult<Customer2Model> findXgjlByUuid(String uuid);

    //长时间未拜访的
    ResponseResult<Customer2Model> updateByZhgjsj(String uuid);

    ResponseResult<List<CustomerModel>> findByZhgjsj(int qzsj);

    //    判断改动并且增加改动记录
    List<Customer3Model> backUpdateById(CustomerModel model, String account);

    //    修改记录
    ResponseResult<PageInfo<Customer3Model>> xgjl3(int pageNow, int pageSize, String cusid);

    ResponseResult<String> exp(CustomerModel model, String filePath);
}
