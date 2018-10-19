package com.springboot.crm.business.txqk.controller;

import com.springboot.crm.business.txqk.model.TxqkModel;
import com.springboot.crm.business.txqk.service.TxqkService;
import com.springboot.crm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/txqk")
public class TxqkController {

    @Autowired
    private TxqkService service;

    @RequestMapping(value = "/txqk", method = RequestMethod.GET)
    public ResponseResult<TxqkModel> find() {
        return service.findAll();
    }

    @RequestMapping(value = "/txqk", method = RequestMethod.POST)
    public ResponseResult<TxqkModel> add(@Valid @ModelAttribute("form") TxqkModel model,
                                         BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        if (model.getQzyrghsj() <= model.getTxsj())
            return new ResponseResult<>(false, "强制时间不能小于等于提醒时间");
        return service.setTxqkSj(model);
    }
}
