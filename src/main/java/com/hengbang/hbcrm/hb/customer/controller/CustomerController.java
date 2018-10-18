package com.hengbang.hbcrm.hb.customer.controller;

import com.auth0.jwt.interfaces.Claim;
import com.github.pagehelper.PageInfo;
import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.hb.customer.model.CusGsrModel;
import com.hengbang.hbcrm.hb.customer.model.Customer2Model;
import com.hengbang.hbcrm.hb.customer.model.Customer3Model;
import com.hengbang.hbcrm.hb.customer.model.CustomerModel;
import com.hengbang.hbcrm.hb.customer.service.CustomerService;
import com.hengbang.hbcrm.sys.shiro.JWTUtils;
import com.hengbang.hbcrm.utils.files.FileTool;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/customer")
public class CustomerController {

    @Value("${page.pageSize}")
    private int pageSize;
    @Value("${file.fileUpPath}")
    private String fileUpPath;
    @Value("${file.fileSuffix}")
    private String fileSuffix;
    @Value("${file.fileInputPath}")
    private String fileInputPath;

    @Autowired
    private CustomerService service;


    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseResult<CustomerModel> save(@Valid @RequestBody CustomerModel model,
                                              BindingResult result,
                                              HttpServletRequest request) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.add(model, request);
    }


    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public ResponseResult<CustomerModel> update(@Valid @RequestBody CustomerModel model,
                                                BindingResult result,
                                                HttpServletRequest request) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.updateGsrById4(model,request);
    }

    //    查询自己的客户
    @RequestMapping(value = "/customer/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<CustomerModel>> page(@PathVariable("pageNow") int pageNow,
                                                        @RequestBody(required = false) CustomerModel model,
                                                        HttpServletRequest request) {
//        指定当前登陆人
        String lTokenD = request.getHeader("LTokenD");
        Map<String, Claim> map = JWTUtils.verifToken(lTokenD);
        String sub = map.get("sub").asString();
        if (model == null)
            model = new CustomerModel();
        model.setGsr(sub);
        return service.findAll(pageNow, pageSize, model);
    }

    //    查询自己的客户-用于首页显示长时间未拜访的客户-非合作客户
    @RequestMapping(value = "/home/customer/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<CustomerModel>> homePage(@PathVariable("pageNow") int pageNow,
                                                            HttpServletRequest request) {
//        指定当前登陆人
        CustomerModel model = new CustomerModel();
        model.setGsr(request.getHeader("LTokenD").split("\\.")[0]);
        return service.findAll4(pageNow, pageSize, model);
    }

    //    查询自己的客户-用于首页显示长时间未拜访的客户-合作客户
    @RequestMapping(value = "/home/customer/hz/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<CustomerModel>> homePageHz(@PathVariable("pageNow") int pageNow) {
//        指定当前登陆人
        Subject subject = SecurityUtils.getSubject();
        AccountModel model1 = (AccountModel) subject.getPrincipal();
        CustomerModel model = new CustomerModel();
        model.setGsr(model1.getUuid());
        return service.findAll4Hz(pageNow, pageSize, model);
    }

    //    冲突检查
//    所有客户flag=0
    @Deprecated
    @RequestMapping(value = "/customer2/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<CustomerModel>> page2(@PathVariable("pageNow") int pageNow,
                                                         @ModelAttribute("form") CustomerModel model) {
        if (model.getGsmc() == null || model.getGsmc().isEmpty())
            return new ResponseResult<>(false, "请输入公司名称");
        return service.findAll(pageNow, pageSize, model);
    }

    //    查询公海的客户
    @RequestMapping(value = "/ghcx/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<CustomerModel>> page3(@PathVariable("pageNow") int pageNow,
                                                         @RequestBody CustomerModel model) {
        model.setGsr("0");
        return service.findAll(pageNow, pageSize, model);
    }

    //    查询本级和下级客户
    @RequestMapping(value = "/customer4/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<CustomerModel>> page4(@PathVariable("pageNow") int pageNow,
                                                         @ModelAttribute("form") CustomerModel model) {
//        指定当前登陆人
        if (model.getGsr() == null || model.getGsr().isEmpty()) {
            Subject subject = SecurityUtils.getSubject();
            AccountModel model1 = (AccountModel) subject.getPrincipal();
            model.setGsr(model1.getUuid());
        }
        return service.findAll2(pageNow, pageSize, model);
    }

    //    查询所有的客户  无论是谁的
//    冲突检查
    @RequestMapping(value = "/ctcx/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<CustomerModel>> page5(@PathVariable("pageNow") int pageNow,
                                                         @RequestBody CustomerModel model) {
        return service.findAll3(pageNow, pageSize, model);
    }


    @RequestMapping(value = "/customer/gsr", method = RequestMethod.PUT)
    public ResponseResult<CustomerModel> updateGsrByUuid(@RequestBody CusGsrModel m,
                                                         HttpServletRequest request) {
        if (m.getGsr().equals("0"))
            if (m.getBz() == null || m.getBz().isEmpty())
                return new ResponseResult<>(false, "清填写调拨备注");
        CustomerModel model = new CustomerModel();
        model.setUuid(m.getUuid());
        model.setGsr(m.getGsr());
        return service.updateGsrById(model, m.getBz(), request);
    }

    //    查看单个客户
    @RequestMapping(value = "/customer/id/{uuid}", method = RequestMethod.GET)
    public ResponseResult<CustomerModel> getById(@PathVariable("uuid") String uuid) {
        return service.getById(uuid);
    }

    //    查看单个客户
    @RequestMapping(value = "/customer/id2/{uuid}", method = RequestMethod.GET)
    public ResponseResult<CustomerModel> getById2(@PathVariable("uuid") String uuid) {
        return service.getById2(uuid);
    }

    //    查看单个客户-必须是自己的

    @RequestMapping(value = "/customer/id3/{uuid}", method = RequestMethod.GET)
    public ResponseResult<CustomerModel> getById3(@PathVariable("uuid") String uuid) {
        return service.getById2(uuid);
    }

    //    查看单个客户

    @RequestMapping(value = "/customer/gsmc/{gsmc}", method = RequestMethod.GET)
    public ResponseResult<CustomerModel> gsmc(@PathVariable("uuid") String uuid) {
        return service.getById2(uuid);
    }

    //    查看客户修改记录

    @RequestMapping(value = "/customer/xgjl/{pageNow}/{uuid}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<Customer2Model>> xgjl(@PathVariable("pageNow") int pageNow,
                                                         @PathVariable("uuid") String uuid) {
        return service.xgjl(pageNow, 5, uuid);
    }

    //    查看客户修改记录-查看详情

    @RequestMapping(value = "/customer/xgjl/{uuid}", method = RequestMethod.GET)
    public ResponseResult<Customer2Model> xgjlByUuid(@PathVariable("uuid") String uuid) {
        return service.findXgjlByUuid(uuid);
    }

    //    上传图片

    @RequestMapping(value = "/upimg", method = RequestMethod.POST)
    public ResponseResult<String> inputs(@NotNull(message = "请选择图片") @RequestParam("files") MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//        判断后缀
        if (fileSuffix == null || fileSuffix.isEmpty())
            return new ResponseResult<>(false, "系统未设置后缀，不能上传");
        if (!fileSuffix.contains(suffix))
            return new ResponseResult<>(false, "不允许的上传类型");

        fileName = GetUuid.getUUID() + "." + suffix;
        FileOutputStream out = null;
        try {
            FileTool.createFolder(fileUpPath);
            try {
                out = new FileOutputStream(fileUpPath + fileName);
                out.write(file.getBytes());
                out.flush();
            } finally {
                if (out != null)
                    out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(false, "图片上传失败");
        }
        return new ResponseResult<>(true, "成功", fileName);
    }

    //    查看客户修改记录

    @RequestMapping(value = "/customer/xgjl3/{pageNow}/{uuid}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<Customer3Model>> xgjl3(@PathVariable("pageNow") int pageNow,
                                                          @PathVariable("uuid") String uuid) {
        return service.xgjl3(pageNow, 5, uuid);
    }

    //    查询自己的客户
    @RequestMapping(value = "/customer/exp", method = RequestMethod.GET)
    public void exp(@ModelAttribute("form") CustomerModel model,
                    HttpServletResponse response) {
//        指定当前登陆人
        Subject subject = SecurityUtils.getSubject();
        AccountModel model1 = (AccountModel) subject.getPrincipal();
        model.setGsr(model1.getUuid());
        ResponseResult<String> exp = service.exp(model, fileInputPath);
        if (exp.isSuccess())
            downloadFile(exp.getData(), response);
    }

    //下载
    private void downloadFile(
            String fileName,
            HttpServletResponse response) {
        try {
//            new String(fileName.getBytes("utf-8"), "iso8859-1")
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=" + StandardCharsets.ISO_8859_1);
            response.setHeader("Content-Disposition",
                    "attachment;filename="
                            + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os;
            try {
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(new File(fileInputPath, fileName)));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
