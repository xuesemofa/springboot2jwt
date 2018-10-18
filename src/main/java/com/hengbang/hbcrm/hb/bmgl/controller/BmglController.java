package com.hengbang.hbcrm.hb.bmgl.controller;

import com.hengbang.hbcrm.hb.bmgl.model.BmglModel;
import com.hengbang.hbcrm.hb.bmgl.service.BmglService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
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
@RequestMapping("/data/bmgl")
public class BmglController {

    @Autowired
    private BmglService service;


    @RequestMapping(value = "/parents/{parents}", method = RequestMethod.GET)
    public ResponseResult<List<BmglModel>> parents(@PathVariable("parents") String parents) {
        return service.findByParents(parents);
    }


    @RequestMapping(value = "/bmgl", method = RequestMethod.POST)
    public ResponseResult<BmglModel> add(@ModelAttribute("form") BmglModel model) {
        if (model.getMc() == null || model.getMc().isEmpty())
            return new ResponseResult<>(false, "团队名称不能为空");
        return service.add(model);
    }


    @RequestMapping(value = "/bmgl/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<BmglModel> deleteById(@PathVariable("uuid") String uuid) {
        return service.deleteById(uuid);
    }


    @RequestMapping(value = "/bmgl", method = RequestMethod.PUT)
    public ResponseResult<BmglModel> updateById(@ModelAttribute("form") BmglModel model) {
        if (model.getMc() == null || model.getMc().isEmpty())
            return new ResponseResult<>(false, "团队名称不能为空");
        return service.updateById(model);
    }


    @RequestMapping(value = "/bmgl2", method = RequestMethod.PUT)
    public ResponseResult<BmglModel> updateById2(@RequestParam("uuid") String uuid,
                                                 @RequestParam("mc") String mc) {
        if (mc == null || mc.isEmpty())
            return new ResponseResult<>(false, "团队名称不能为空");
        BmglModel model = new BmglModel();
        model.setUuid(uuid);
        model.setMc(mc);
        return service.updateById(model);
    }


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseResult<List<BmglModel>> findAll() {
        return service.findAll();
    }
}
