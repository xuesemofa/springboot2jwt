package com.hengbang.hbcrm.hb.qygg.controller;

import com.hengbang.hbcrm.hb.qygg.model.QyggModel;
import com.hengbang.hbcrm.hb.qygg.service.QyggService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
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
