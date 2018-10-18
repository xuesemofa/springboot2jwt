package com.hengbang.hbcrm.hb.gjfs.controller;

import com.hengbang.hbcrm.hb.gjfs.model.GjfsModel;
import com.hengbang.hbcrm.hb.gjfs.service.GjfsService;
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
@RequestMapping("/data/gjfs")
public class GjfsController {

    @Autowired
    private GjfsService service;


    @RequestMapping(value = "/gjfs", method = RequestMethod.POST)
    public ResponseResult<GjfsModel> add(@Valid @ModelAttribute("form") GjfsModel model,
                                         BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        return service.add(model);
    }


    @RequestMapping(value = "/gjfs", method = RequestMethod.PUT)
    public ResponseResult<GjfsModel> updateById(@Valid @ModelAttribute("form") GjfsModel model,
                                                BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        return service.updateById(model);
    }


    @RequestMapping(value = "/gjfs", method = RequestMethod.GET)
    public ResponseResult<List<GjfsModel>> findAll() {
        return service.findAll();
    }
}
