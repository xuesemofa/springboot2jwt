package com.springboot.crm.business.qygg.controller;

import com.springboot.crm.business.qygg.model.QyggModel;
import com.springboot.crm.business.qygg.service.QyggService;
import com.springboot.crm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/qygg")
public class QyggController {

    @Autowired
    private QyggService service;

    @RequestMapping(value = "qygg", method = RequestMethod.GET)
    public ResponseResult<QyggModel> qygg() {
        return service.findLack();
    }
}
