package com.hengbang.hbcrm.hb.gjjl.controller;

import com.github.pagehelper.PageInfo;
import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.hb.gjjl.model.GjjlModel;
import com.hengbang.hbcrm.hb.gjjl.model.GjjlQueryModel;
import com.hengbang.hbcrm.hb.gjjl.servicve.GjjlService;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/gjjl")
public class GjjlController {

    @Autowired
    private GjjlService service;


    @RequestMapping(value = "/gjjl", method = RequestMethod.POST)
    public ResponseResult<GjjlModel> add(@Valid @ModelAttribute("form") GjjlModel model,
                                         BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        Subject subject = SecurityUtils.getSubject();
        AccountModel model1 = (AccountModel) subject.getPrincipal();
        model.setGjr(model1.getUuid());
        if (model.getGjsj() == null)
            model.setGjsj(new Timestamp(System.currentTimeMillis()));
        return service.add(model);
    }


    @RequestMapping(value = "/gjjl/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<GjjlModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                       @RequestParam(value = "cusid", required = false) String cusid,
                                                       @RequestParam(value = "gjr", required = false) String gjr,
                                                       @RequestParam(value = "gjsjStr", required = false) Date gjsjStr,
                                                       @RequestParam(value = "gjsjEnd", required = false) Date gjsjEnd,
                                                       @RequestParam(value = "gjfs", required = false) String gjfs) {
        GjjlQueryModel model = new GjjlQueryModel();
        if (cusid != null && !cusid.isEmpty())
            model.setCusid(cusid);
        if (gjr != null && !gjr.isEmpty())
            model.setGjr(gjr);
        if (gjfs != null && !gjfs.isEmpty())
            model.setGjfs(gjfs);
        if (gjsjStr != null)
            model.setGjsjStr(gjsjStr);
        if (gjsjEnd != null)
            model.setGjsjEnd(gjsjEnd);
        if (gjsjStr != null && gjsjEnd != null)
            if (gjsjStr.after(gjsjEnd))
                return new ResponseResult<>(false, "跟进开始时间不能大于结束时间");
        return service.findAll(pageNow, 5, model);
    }

    @RequestMapping(value = "/gjjl2/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<GjjlModel>> findAll2(@PathVariable("pageNow") int pageNow,
                                                        @RequestParam(value = "cusid", required = false) String cusid,
                                                        @RequestParam(value = "gjr", required = false) String gjr,
                                                        @RequestParam(value = "gjsjStr", required = false) Date gjsjStr,
                                                        @RequestParam(value = "gjsjEnd", required = false) Date gjsjEnd,
                                                        @RequestParam(value = "gjfs", required = false) String gjfs) {
        GjjlQueryModel model = new GjjlQueryModel();
        if (cusid != null && !cusid.isEmpty())
            model.setCusid(cusid);
        if (gjr != null && !gjr.isEmpty())
            model.setGjr(gjr);
        if (gjfs != null && !gjfs.isEmpty())
            model.setGjfs(gjfs);
        if (gjsjStr != null)
            model.setGjsjStr(gjsjStr);
        if (gjsjEnd != null)
            model.setGjsjEnd(gjsjEnd);
        if (gjsjStr != null && gjsjEnd != null)
            if (gjsjStr.after(gjsjEnd))
                return new ResponseResult<>(false, "跟进开始时间不能大于结束时间");
        return service.findAll2(pageNow, 5, model);
    }
}
