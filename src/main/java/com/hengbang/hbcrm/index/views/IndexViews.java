package com.hengbang.hbcrm.index.views;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
public class IndexViews {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/views/login/login");
    }

}
