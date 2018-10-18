package com.hengbang.hbcrm.hb.jurisdiction.controller;

import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
import com.hengbang.hbcrm.hb.jurisdiction.service.JurisdictionService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/jurisdiction")
public class JurisdictionController {

    @Autowired
    private JurisdictionService service;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseResult<List<JurisdictionModel>> findAll() {
        return service.findAll();
    }
}
