package com.springboot.crm.sys.shiro;

import com.springboot.crm.utils.JackJson.JackJson;
import com.springboot.crm.utils.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * filter all requests
 */
@Slf4j
public class StatelessAuthcFilter extends AccessControlFilter {

    /**
     * Only the current method returns to false,
     * and the following method will execute.
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        String url = getPathWithinApplication(servletRequest);
        if (url.equals("/"))
            return true;
        List<String> list = getList();
        for (int i = 0; i < list.size(); i++) {
            if (url.contains(list.get(i))) {
                return true;
            }
        }
        log.info("url==>" + url + " REQUESTHOST==>" + servletRequest.getRemoteHost());
        return false;
    }

    /**
     * the current metod returns to false to end the current request.
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        To get token,you can use head/Cokie or others.
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String lTokenD = request.getHeader("LTokenD");
        if (lTokenD == null || lTokenD.isEmpty())
            tokenError(servletRequest, servletResponse);
        else {
//            Generate stateless token
            StatelessToken token = new StatelessToken();
            token.setToken(lTokenD);
//                Entrust realm to login
            try {
                getSubject(servletRequest, servletResponse).login(token);
                return true;
            } catch (Exception e) {
                tokenError(servletRequest, servletResponse);
            }
        }
        return false;
    }

    //    Is no Token
    private void tokenError(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        if (isAjax(servletRequest)) {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        ResponseResult<String> result = new ResponseResult<>(false, "-1");
        httpServletResponse.getWriter().write(Objects.requireNonNull(JackJson.beanToJson(result)));
//        } else {
//              非ajax请求重定向为登录页面
//            httpServletResponse.sendRedirect("/views/login/login");
//        }
    }

    //  Is it Ajax
    private boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("/data/login");
//        list.add("/views/");
//        list.add("/home/index");
//        list.add("/favicon.ico");
//        list.add("/HBfavicon.ico");
//        list.add("/XHfavicon.ico");
//        list.add("/Angular");
//        list.add("/css");
//        list.add("/email_templates");
//        list.add("/font-awesome");
//        list.add("/fonts");
//        list.add("/img");
//        list.add("/js");
//        list.add("/style");
//        list.add("/defaultKaptcha");
//        list.add("/getObject");
//        list.add("/toastr");
        return list;
    }
}
