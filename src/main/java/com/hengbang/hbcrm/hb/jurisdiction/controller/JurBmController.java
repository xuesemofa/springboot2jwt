package com.hengbang.hbcrm.hb.jurisdiction.controller;

import com.auth0.jwt.interfaces.Claim;
import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.hb.account.service.AccountService;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurBmModel;
import com.hengbang.hbcrm.hb.jurisdiction.model.JurisdictionModel;
import com.hengbang.hbcrm.hb.jurisdiction.service.JurBmService;
import com.hengbang.hbcrm.sys.shiro.JWTUtils;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/jurbm")
public class JurBmController {

    @Autowired
    private JurBmService service;
    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/jurbm/{jurid}/{bmid}", method = RequestMethod.GET)
    public ResponseResult<JurBmModel> isQx(@PathVariable("jurid") String jurid,
                                           @PathVariable("bmid") String bmid) {
        JurBmModel model = new JurBmModel();
        model.setBmId(bmid);
        model.setJurId(jurid);
        return service.isQx(model);
    }


    @RequestMapping(value = "/bmid/{bmid}", method = RequestMethod.GET)
    public ResponseResult<List<JurBmModel>> findByBmId(@PathVariable("bmid") String bmid) {
        return service.findByBmId(bmid);
    }

    @RequestMapping(value = "/jur", method = RequestMethod.GET)
    public ResponseResult<List<JurisdictionModel>> findByBmId2(HttpServletRequest request) {
        String lTokenD = request.getHeader("LTokenD");
        Claim sub = JWTUtils.getApp(lTokenD, "sub");
        ResponseResult<AccountModel> result = accountService.getByUuid(sub.asString());
        if (result.isSuccess()) {
            if (result.getData().getLx() == -1)
                return new ResponseResult<>(true, result.getData().getLx() + "", new ArrayList<>());
            ResponseResult<List<JurisdictionModel>> result1 = service.findByBmId2(result.getData().getBmid());
            return new ResponseResult<>(result1.isSuccess(), result.getData().getLx() + "", result1.getData());
        } else {
            return new ResponseResult<>(false, "no BM");
        }
    }
}
