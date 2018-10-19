package com.springboot.crm.business.khly.controller;

import com.springboot.crm.business.khly.model.KhlyModel;
import com.springboot.crm.business.khly.service.KhlyService;
import com.springboot.crm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/khly")
public class KhlyController {

    @Autowired
    private KhlyService service;


    @RequestMapping(value = "/khly", method = RequestMethod.POST)
    public ResponseResult<KhlyModel> add(@Valid @ModelAttribute("form") KhlyModel model,
                                         BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        return service.add(model);
    }


    @RequestMapping(value = "/khly", method = RequestMethod.PUT)
    public ResponseResult<KhlyModel> updateById(@Valid @ModelAttribute("form") KhlyModel model,
                                                BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        return service.updateById(model);
    }


    @RequestMapping(value = "/khly", method = RequestMethod.GET)
    public ResponseResult<List<KhlyModel>> findAll() {
        return service.findAll();
    }
}
