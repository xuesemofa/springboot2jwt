package com.hengbang.hbcrm.utils.file.controller;

import com.hengbang.hbcrm.utils.result.ResponseResult;
import com.hengbang.hbcrm.utils.uuidUtil.GetUuid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.fileUpPath}")
    private String filePath;

    /**
     * RedirectAttributes redirectAttributes 重定向
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseResult<String> upload(@RequestParam("file") MultipartFile file) {
        if (file == null)
            return new ResponseResult<>(false, "文件不能为空", null);

        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        fileName = GetUuid.getUUID() + "." + suffix;
        FileOutputStream out = null;
        try {
            try {
                out = new FileOutputStream(filePath + fileName);
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
        return new ResponseResult<>(true, "成功", fileName);
    }

    /**
     * 下载文件
     *
     * @param fileName --文件完整路径
     * @param response
     */
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public void downloadFile(
            String fileName,
            javax.servlet.http.HttpServletResponse response) {
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
                bis = new BufferedInputStream(new FileInputStream(new File(filePath, fileName)));
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
