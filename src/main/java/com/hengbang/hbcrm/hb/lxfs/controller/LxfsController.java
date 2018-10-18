package com.hengbang.hbcrm.hb.lxfs.controller;

import com.github.pagehelper.PageInfo;
import com.hengbang.hbcrm.hb.lxfs.model.LxfsModel;
import com.hengbang.hbcrm.hb.lxfs.service.LxfsService;
import com.hengbang.hbcrm.utils.files.FileTool;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/data/lxfs")
public class LxfsController {

    @Value("${file.fileUpPath}")
    private String fileUpPath;
    @Value("${file.fileSuffix}")
    private String fileSuffix;
    @Autowired
    private LxfsService service;


    @RequestMapping(value = "/lxfs/{pageNow}/{cusid}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<LxfsModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                       @PathVariable("cusid") String cusid) {
        LxfsModel model = new LxfsModel();
        model.setCusid(cusid);
        return service.findAll(pageNow, 5, model);
    }


    @RequestMapping(value = "/lxfs2/{cusid}", method = RequestMethod.GET)
    public ResponseResult<List<LxfsModel>> findAll2(@PathVariable("cusid") String cusid) {
        LxfsModel model = new LxfsModel();
        model.setCusid(cusid);
        return service.findAll2(model);
    }


    @RequestMapping(value = "/lxfs", method = RequestMethod.POST)
    public ResponseResult<LxfsModel> add(@Valid @ModelAttribute("form") LxfsModel model,
                                         BindingResult result) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        return service.add(model);
    }


    @RequestMapping(value = "/lxfs/{uuid}", method = RequestMethod.PUT)
    public ResponseResult<LxfsModel> updateById(@Valid @ModelAttribute("form") LxfsModel model,
                                                BindingResult result,
                                                @PathVariable("uuid") String uuid) {
        if (result.hasErrors())
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        model.setUuid(uuid);
        return service.updateById(model);
    }


    @RequestMapping(value = "/lxfs/{uuid}", method = RequestMethod.GET)
    public ResponseResult<LxfsModel> getById(@PathVariable("uuid") String uuid) {
        return service.getById(uuid);
    }

    //    上传图片

    @RequestMapping(value = "/upimg", method = RequestMethod.POST)
    public ResponseResult<String> inputs(@NotNull(message = "请选择图片") @RequestParam("files") MultipartFile file) {
        if (file.isEmpty())
            return new ResponseResult<>(false, "文件不能为空");

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//        判断后缀
        if (fileSuffix == null || fileSuffix.isEmpty())
            return new ResponseResult<>(false, "系统未设置后缀，不能上传");
        if (!(fileSuffix.indexOf(suffix) > 0))
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
}
