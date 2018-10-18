package com.hengbang.hbcrm.views;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views")
public class ViewsController {

    @Value("${loginHtml}")
    private String loginHtml;

    @RequestMapping("/{a}/{b}")
    public String views(@PathVariable("a") String a,
                        @PathVariable("b") String b) {
        if (a.equals("login") && b.equals("login"))
            return "/" + a + "/" + loginHtml;
        return "/" + a + "/" + b;
    }
}
