package com.hengbang.hbcrm.home.views;

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
@RequestMapping("/home")
public class HomeViews {

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/home/index");
    }
}
