package com.hengbang.hbcrm.hb.login.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hengbang.hbcrm.hb.account.model.AccountModel;
import com.hengbang.hbcrm.hb.account.service.AccountService;
import com.hengbang.hbcrm.hb.login.model.LoginModel;
import com.hengbang.hbcrm.sys.shiro.JWTUtils;
import com.hengbang.hbcrm.utils.base64.Base64Util;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@RestController
@RequestMapping("/data/login")
public class LoginController {

    @Value("${loginHtml}")
    private String loginHtml;

    public static Map<String, Integer> map = new HashMap<>();

    @Autowired
    private AccountService service;
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult<String> login(@Valid @RequestBody LoginModel model,
                                        BindingResult result,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
//        验证输入的信息
        if (result.hasErrors())
            return new ResponseResult<>(false,
                    result.getAllErrors().get(0).getDefaultMessage());

        ResponseResult<AccountModel> result1 = service.getByAccount(model.getAccount());
        if (result1.isSuccess()) {
            boolean b1 = result1.getData().getIsLogin().equals("Y");
            if (!b1 && result1.getData().getLx() != -1)
                return new ResponseResult<>(false, "当前账号禁止登录");
            else {
                String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
                String encode = Base64Util.encode(md5Password);
                if (encode.equals(result1.getData().getPassword())) {
                    try {
                        String s = JWTUtils.creaToken(result1.getData().getUuid());
                        response.setHeader("LTokenD", s);
                        return new ResponseResult<>(true, result1.getData().getUuid(), s);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new ResponseResult<>(false, "令牌生成错误");
                    }
                } else
                    return new ResponseResult<>(false, "账号密码错误");
            }
        } else
            return new ResponseResult<>(false, "账号密码错误");
    }

    @RequestMapping(value = "/defaultKaptcha", method = RequestMethod.GET)
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    //    验证码验证
    public boolean imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        if (captchaId == null)
            return false;
        String parameter = httpServletRequest.getParameter("vrifyCode");
        if (!captchaId.equals(parameter))
            return false;
        else
            return true;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletResponse response) {
        SecurityUtils.getSubject().logout();
        return new ModelAndView("/index");
    }

}
