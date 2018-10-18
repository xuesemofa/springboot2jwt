package com.hengbang.hbcrm.hb.dywh.controller;

import com.hengbang.hbcrm.hb.dywh.model.DywhModel;
import com.hengbang.hbcrm.hb.dywh.service.DywhService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/dywh")
public class DywhController {

    @Autowired
    private DywhService service;


    @RequestMapping(value = "/dywh/{parents}", method = RequestMethod.GET)
    public ResponseResult<List<DywhModel>> parents(@PathVariable("parents") String parents) {
        return service.findByParents(parents);
    }


    @RequestMapping(value = "/dywh", method = RequestMethod.POST)
    public ResponseResult<DywhModel> add(@Valid @ModelAttribute("form") DywhModel model,
                                         BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.add(model);
    }


    @RequestMapping(value = "/dywh/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<DywhModel> deleteById(@PathVariable("uuid") String uuid) {
        return service.deleteById(uuid);
    }


    @RequestMapping(value = "/dywh", method = RequestMethod.PUT)
    public ResponseResult<DywhModel> updateById(@ModelAttribute("form") DywhModel model) {
        return service.updateById(model);
    }


    @RequestMapping(value = "/dywh2", method = RequestMethod.PUT)
    public ResponseResult<DywhModel> updateById2(@RequestParam("uuid") String uuid,
                                                 @RequestParam("mc") String mc) {
        if (mc == null || mc.isEmpty())
            return new ResponseResult<>(false, "名称不能为空");
        DywhModel model = new DywhModel();
        model.setUuid(uuid);
        model.setMc(mc);
        return service.updateById(model);
    }
}
