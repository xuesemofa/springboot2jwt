package com.springboot.crm.business.accJur.controller;

import com.springboot.crm.business.accJur.service.AccJurService;
import com.springboot.crm.business.jurisdiction.model.JurisdictionModel;
import com.springboot.crm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/accJur")
public class AccJurController {

    @Autowired
    private AccJurService service;


    @RequestMapping(value = "/accid/{accid}", method = RequestMethod.GET)
    public ResponseResult<List<JurisdictionModel>> findByAccid(@PathVariable("accid") String accid) {
        List<JurisdictionModel> list = service.findByAccId(accid);
        if (list != null && list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }

    //授权、反授权

    @RequestMapping(value = "/accJur", method = RequestMethod.POST)
    public ResponseResult<JurisdictionModel> sq(@RequestParam("jurid") String jurid,
                                                @RequestParam("accid") String accid) {
        int i = service.sq(jurid, accid);
        if (i > 0)
            return new ResponseResult<>(true, "成功", null);
        else
            return new ResponseResult<>(false, "未查询到记录", null);
    }
}
