package com.springboot.crm.home.controller;

import com.springboot.crm.business.bmgl.model.BmglModel;
import com.springboot.crm.business.bmgl.service.BmglService;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.sessions.SessionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/home")
public class HomeController {

    @Autowired
    private BmglService bmglService;

    //    获取当前登陆人的职位
    @RequestMapping(value = "/zw", method = RequestMethod.GET)
    public ResponseResult<BmglModel> zw(HttpServletRequest request) {
        String lTokenD = request.getHeader("LTokenD");
        return bmglService.findByUuid(lTokenD.split("\\.")[0]);
    }

    @RequestMapping(value = "/zxrs", method = RequestMethod.GET)
    public ResponseResult<String> zxrs() {
        Map<String, String> map = SessionsUtil.map;
        return new ResponseResult<>(true, "", map.size() + "");
    }
}
