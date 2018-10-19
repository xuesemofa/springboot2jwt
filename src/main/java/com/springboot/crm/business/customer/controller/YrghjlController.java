package com.springboot.crm.business.customer.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.crm.business.account.service.AccountService;
import com.springboot.crm.business.customer.model.YrghJlModel;
import com.springboot.crm.business.customer.service.YrghjlService;
import com.springboot.crm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/yrghjl")
public class YrghjlController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Autowired
    private YrghjlService service;
    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/yrghjl/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<YrghJlModel>> page(@PathVariable("pageNow") int pageNow,
                                                      @RequestParam("uuid") String uuid) {
        YrghJlModel yrghJlModel = new YrghJlModel();
        yrghJlModel.setBdddkh(uuid);
        return service.findAll(pageNow, 5, yrghJlModel);
    }
}
