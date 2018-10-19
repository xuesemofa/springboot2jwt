package com.springboot.crm.business.khdr.controller;

import com.springboot.crm.business.khdr.service.KhdrService;
import com.springboot.crm.utils.files.FileTool;
import com.springboot.crm.utils.result.ResponseResult;
import com.springboot.crm.utils.uuidUtil.GetUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.FileOutputStream;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */

@EnableAsync//springboot 特有的异步处理注解，此注解紧用于类且必须是controller;注意，这里的异步方法，只能在自身之外调用，在本类调用是无效的。
@RestController
@RequestMapping("/data/khdr")
public class KhdrController {

    @Value("${file.fileInputPath}")
    private String inputPath;

    @Autowired
    private KhdrService service;


    @RequestMapping(value = "/khdr", method = RequestMethod.POST)
    public ResponseResult<Integer> inputs(@NotNull(message = "请选择文件") @RequestParam("files") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        fileName = GetUuid.getUUID() + "." + suffix;
        FileOutputStream out = null;
        try {
            FileTool.createFolder(inputPath);
            try {
                out = new FileOutputStream(inputPath + fileName);
                out.write(file.getBytes());
                out.flush();
            } finally {
                if (out != null)
                    out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(false, "文件上传失败", null);
        }
//        return new ResponseResult<>(true, "成功", fileName);
        ResponseResult<Integer> result = service.jxXLS(inputPath, fileName);
//        return new ResponseResult<>(result.isSuccess(), result.getMessage(), result.getData());
        return new ResponseResult<>(true, "已开始处理");
    }

}
