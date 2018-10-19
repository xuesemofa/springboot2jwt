package com.springboot.crm.business.hgbm.controller;

import com.springboot.crm.business.hgbm.model.HgbmModel;
import com.springboot.crm.business.hgbm.service.HgbmService;
import com.springboot.crm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/data/hgbm")
public class HgbmController {

    @Autowired
    private HgbmService service;


    @RequestMapping(value = "/hgbm", method = RequestMethod.POST)
    public ResponseResult<HgbmModel> add(@Valid @ModelAttribute("from") HgbmModel model,
                                         BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.add(model);
    }


    @RequestMapping(value = "/hgbm", method = RequestMethod.PUT)
    public ResponseResult<HgbmModel> updateByUuid(@RequestParam("uuid") String uuid,
                                                  @RequestParam("mc") String mc) {
        if (uuid.isEmpty() || mc.isEmpty())
            return new ResponseResult<>(false, "名称不能为空");
        HgbmModel model = new HgbmModel();
        model.setUuid(uuid);
        model.setMc(mc);
        return service.updateByUuid(model);
    }


    @RequestMapping(value = "/hgbm/{parents}", method = RequestMethod.GET)
    public ResponseResult<List<HgbmModel>> findByParents(@PathVariable("parents") String parents) {
        return service.findByParents(parents);
    }
}
