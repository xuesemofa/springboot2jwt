package com.hengbang.hbcrm.hb.login.view;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/views/login")
public class LoginView {

    @GetMapping("/logout")
    public ModelAndView logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return new ModelAndView("redirect:/index");
    }
}
