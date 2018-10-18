package com.hengbang.hbcrm.hb.gjpl.controller;

import com.hengbang.hbcrm.hb.gjpl.model.GjplModel;
import com.hengbang.hbcrm.hb.gjpl.service.GjplService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/data/gjpl")
public class GjplController {

    @Autowired
    private GjplService service;


    @RequestMapping(value = "/gjpl/{lx}", method = RequestMethod.GET)
    public ResponseResult<GjplModel> getGjplByLx(@PathVariable("lx") int lx) {
        return service.getByLx(lx);
    }


    @RequestMapping(value = "/gjpl", method = RequestMethod.POST)
    public ResponseResult<GjplModel> GjplByLx(@Valid @ModelAttribute("form") GjplModel model,
                                              BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.setGjpl(model);
    }
}
