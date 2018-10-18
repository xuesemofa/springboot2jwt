package com.hengbang.hbcrm.hb.khslxz.controller;

import com.hengbang.hbcrm.hb.khslxz.model.KhslxzModel;
import com.hengbang.hbcrm.hb.khslxz.service.KhslxzService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/data/khslxz")
public class KhslxzController {

    @Autowired
    private KhslxzService service;

    @RequestMapping(value = "/khslxz", method = RequestMethod.GET)
    public ResponseResult<List<KhslxzModel>> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/khslxz", method = RequestMethod.POST)
    public ResponseResult<KhslxzModel> setSl(@Valid @ModelAttribute("form") KhslxzModel model,
                                             BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getFieldErrors().get(0).getDefaultMessage());
        return service.setSl(model);
    }
}
