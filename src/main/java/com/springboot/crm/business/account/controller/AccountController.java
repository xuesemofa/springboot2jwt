package com.springboot.crm.business.account.controller;

import com.auth0.jwt.interfaces.Claim;
import com.github.pagehelper.Page;
import com.springboot.crm.business.account.model.AccountModel;
import com.springboot.crm.business.account.service.AccountService;
import com.springboot.crm.sys.shiro.JWTUtils;
import com.springboot.crm.utils.base64.Base64Util;
import com.springboot.crm.utils.result.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/account")
public class AccountController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private AccountService service;


    @RequestMapping(value = "/account/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<Page<AccountModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                      @ModelAttribute("form") AccountModel model) {
        Subject subject = SecurityUtils.getSubject();
        AccountModel model1 = (AccountModel) subject.getPrincipal();
        model.setParents(model1.getUuid());
        return service.findAll(pageNow, pageSize, model);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseResult<AccountModel> getById(HttpServletRequest request) {
        String lTokenD = request.getHeader("LTokenD");
        Claim sub = JWTUtils.getApp(lTokenD, "sub");
        return service.getByUuid(sub.asString());
    }


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseResult<AccountModel> add(@Valid @ModelAttribute("form") AccountModel model,
                                            BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        Subject subject = SecurityUtils.getSubject();
        AccountModel model1 = (AccountModel) subject.getPrincipal();
        model.setIsLogin("Y");
        model.setCreatorAccId(model1.getUuid());
        model.setSystimes(new Timestamp(System.currentTimeMillis()));
        //对密码进行 md5 加密
        String md5Password = DigestUtils.md5DigestAsHex("hengbanghuirong".getBytes(StandardCharsets.UTF_8));
        model.setPassword(Base64Util.encode(md5Password));
        return service.add(model);
    }


    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<AccountModel> deleteById(@PathVariable("uuid") String uuid) {
        return service.deleteById(uuid);
    }


    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updateById(@Valid @ModelAttribute("form") AccountModel model,
                                                   BindingResult result) {
//        if (result.hasErrors())
//            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage(), null);
        return service.updateById(model, false);
    }


    @RequestMapping(value = "/isLogin", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> isLogin(@RequestParam("uuid") String uuid,
                                                @RequestParam("isLogin") String isLogin) {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        model.setIsLogin(isLogin);
        return service.updateById(model, false);
    }


    @RequestMapping(value = "/pwd", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> updateByAccount(HttpServletRequest request,
                                                        @RequestBody AccountModel model) {
        if (!model.getPassword().isEmpty()) {
            String lTokenD = request.getHeader("LTokenD");
            Claim sub = JWTUtils.getApp(lTokenD, "sub");
            String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
            String s = sub.asString();
            model.setPassword(Base64Util.encode(md5Password));
            model.setUuid(s);
            return service.updateById(model, false);
        } else
            return new ResponseResult<>(false, "两次密码不一致，并且密码不能为空", null);
    }


    @RequestMapping(value = "/parents/{p}", method = RequestMethod.GET)
    public ResponseResult<List<AccountModel>> parents(@PathVariable("p") String p) {
        return service.findByParents(p);
    }


    @RequestMapping(value = "/parents2/0", method = RequestMethod.GET)
    public ResponseResult<List<AccountModel>> parents2() {
        Subject subject = SecurityUtils.getSubject();
        AccountModel model1 = (AccountModel) subject.getPrincipal();
        return service.findByParents2(model1.getUuid());
    }


    @RequestMapping(value = "/dg", method = RequestMethod.POST)
    public ResponseResult<AccountModel> dg(@RequestParam("a") String a,
                                           @RequestParam("b") String b) {
        if (a.equals(b))
            return new ResponseResult<>(false, "被调岗和目标岗不能一至");
        else {
            ResponseResult<List<AccountModel>> list = service.findByParents(a);
            if (list.isSuccess())
                return new ResponseResult<>(false, "存在下级不允许调岗");
            AccountModel model = new AccountModel();
            model.setUuid(a);
            model.setParents(b);
            return service.updateById(model, true);
        }
    }


    @RequestMapping(value = "/zzjgdg", method = RequestMethod.POST)
    public ResponseResult<AccountModel> zzjgdg(@RequestParam("a") String a,
                                               @RequestParam("b") String b) {
        if (a.equals(b))
            return new ResponseResult<>(false, "被调岗和目标岗不能一至");
        else {
            AccountModel model = new AccountModel();
            model.setUuid(a);
            model.setBmid(b);
            return service.updateById(model, true);
        }
    }

    @RequestMapping(value = "/czmm", method = RequestMethod.PUT)
    public ResponseResult<AccountModel> czmm(@RequestParam("uuid") String uuid) {
        AccountModel model = new AccountModel();
        model.setUuid(uuid);
        String md5Password = DigestUtils.md5DigestAsHex("hengbanghuirong".getBytes(StandardCharsets.UTF_8));
        String encode = Base64Util.encode(md5Password);
        model.setPassword(encode);
        return service.updateById(model, false);
    }

    @RequestMapping(value = "/sjh", method = RequestMethod.GET)
    public ResponseResult<AccountModel> sjh(@RequestParam("sjh") String sjh) {
        if (sjh == null || sjh.isEmpty() || sjh.length() < 11 || sjh.length() > 11)
            return new ResponseResult<>(false, "不是合法的手机号长度");
        Subject subject = SecurityUtils.getSubject();
        AccountModel model1 = (AccountModel) subject.getPrincipal();
        AccountModel model = new AccountModel();
        model.setUuid(model1.getUuid());
        model.setPhone(sjh);
        return service.updateById(model, false);
    }
}
