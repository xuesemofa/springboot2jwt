package com.hengbang.hbcrm.hb.khdj.controller;

import com.hengbang.hbcrm.hb.khdj.model.KhdjModel;
import com.hengbang.hbcrm.hb.khdj.service.KhdjService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
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
@RequestMapping("/data/khdj")
public class KhdjController {

    @Autowired
    private KhdjService service;


    @RequestMapping(value = "/khdj", method = RequestMethod.POST)
    public ResponseResult<KhdjModel> add(@Valid @ModelAttribute("form") KhdjModel model,
                                         BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        return service.add(model);
    }


    @RequestMapping(value = "/khdj", method = RequestMethod.PUT)
    public ResponseResult<KhdjModel> updateById(@Valid @ModelAttribute("form") KhdjModel model,
                                                BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        return service.updateById(model);
    }


    @RequestMapping(value = "/khdj", method = RequestMethod.GET)
    public ResponseResult<List<KhdjModel>> findAll() {
        return service.findAll();
    }
}
