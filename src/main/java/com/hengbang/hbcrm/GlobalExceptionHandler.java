package com.hengbang.hbcrm;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hengbang.hbcrm.utils.JackJson.JackJson;
import com.hengbang.hbcrm.utils.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.InvalidSessionException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.exceptions.TemplateInputException;
import org.thymeleaf.exceptions.TemplateProcessingException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.NoRouteToHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

//import org.hibernate.JDBCException;

/**
 * 全局错误处理
 * LD
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    //    判断请求是否是ajax
    private boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 会话过期
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public void incorrectCredentialsException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(403);
            ResponseResult<String> result = new ResponseResult<>(false, "password error");
            response.getWriter().write(Objects.requireNonNull(JackJson.beanToJson(result)));
        } else {
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
            response.sendRedirect("/views/login/login");
        }
    }

    /**
     * 会话过期
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = TokenExpiredException.class)
    public void tokenExpiredException(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(403);
            ResponseResult<String> result = new ResponseResult<>(false, "令牌过期");
            response.getWriter().write(Objects.requireNonNull(JackJson.beanToJson(result)));
        } else {
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
            response.sendRedirect("/views/login/login");
        }
    }

    /**
     * 会话过期
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = InvalidSessionException.class)
    public void invalidSessionException(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(403);
            ResponseResult<String> result = new ResponseResult<>(false, "请从新登录");
            response.getWriter().write(Objects.requireNonNull(JackJson.beanToJson(result)));
        } else {
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
            response.sendRedirect("/views/login/login");
        }
    }

    /**
     * 数据库无法连接
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = PersistenceException.class)
    public void persistenceException(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(403);
            ResponseResult<String> result = new ResponseResult<>(false, "数据库无法连接");
            response.getWriter().write(Objects.requireNonNull(JackJson.beanToJson(result)));
        } else {
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
            response.sendRedirect("/views/login/login");
        }
    }

    /**
     * 没有到主机的路由
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = NoRouteToHostException.class)
    public void noRouteToHostException(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(403);
            ResponseResult<String> result = new ResponseResult<>(false, "没有到主机的路由");
            response.getWriter().write(Objects.requireNonNull(JackJson.beanToJson(result)));
//            httpServletResponse.sendRedirect("/index");
        } else {
            //saveRequestAndRedirectToLogin(request, response);
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
            response.sendRedirect("/views/login/login");
        }
    }

    /**
     * 非法参数
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ParseException.class)
    public ResponseResult<String> parseException(HttpServletRequest request,
                                                 Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据转换错误，请确认日期，数字等格式是否正确");
        return result;
    }

    /**
     * 非法参数
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseResult<String> illegalArgumentException(HttpServletRequest request,
                                                           Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("非法参数错误，请确认数据准确性");
        return result;
    }


    /**
     * 不支持的字符集
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = UnsupportedEncodingException.class)
    public ResponseResult<String> unsupportedEncodingException(HttpServletRequest request,
                                                               Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("不支持的字符集，当前方法所采用的字符集为UTF-8");
        return result;
    }

    /**
     * 权限不足
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
//    @ExceptionHandler(value = AuthorizationException.class)
//    public void authorizationException(HttpServletRequest request,
//                                       HttpServletResponse response,
//                                       Exception exception) throws Exception {
//        //        exception.printStackTrace();
//        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
//        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
////        return new ModelAndView("/403");
//        String requestType = request.getHeader("X-Requested-With");
//        if ("XMLHttpRequest".equals(requestType)) {
//
//            ResponseResult result = new ResponseResult();
//            result.setSuccess(false);
//            result.setMessage(new String("权限不足".getBytes("UTF-8"), "UTF-8"));
//
//            ObjectMapper objectMapper = new ObjectMapper();
////            JsonGenerator jsonGenerator = objectMapper.getFactory()
////                    .createGenerator(System.out, JsonEncoding.UTF8);
//            //对象转JSON
//            String json = objectMapper.writeValueAsString(result);//返回字符串，输出
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-type", "text/html;charset=UTF-8");
//            response.getWriter().append(json);
//        } else {
////            非ajax请求
//            response.sendRedirect("/403");
//        }
//    }

    /**
     * 权限不足
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
//    @ExceptionHandler(value = UnauthorizedException.class)
//    public void UnauthorizedException(HttpServletRequest request,
//                                      Exception exception,
//                                      HttpServletResponse response) throws Exception {
////        exception.printStackTrace();
//        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
//        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
////        return new ModelAndView("/403");
//        String requestType = request.getHeader("X-Requested-With");
//        if ("XMLHttpRequest".equals(requestType)) {
//
//            if (request.getHeader("L") != null) {
////                跳转页面的ajax
//                response.sendRedirect("/403");
//            }
//
//            ResponseResult result = new ResponseResult();
//            result.setSuccess(false);
//            result.setMessage(new String("权限不足!".getBytes("UTF-8"), "UTF-8"));
//
//            ObjectMapper objectMapper = new ObjectMapper();
////            JsonGenerator jsonGenerator = objectMapper.getFactory()
////                    .createGenerator(System.out, JsonEncoding.UTF8);
//            //对象转JSON
//            String json = objectMapper.writeValueAsString(result);//返回字符串，输出
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-type", "text/html;charset=UTF-8");
//            response.getWriter().append(json);
//        } else {
////            非ajax请求
//            response.sendRedirect("/403");
//        }
//    }
//

    /**
     * 未登录
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = UnknownAccountException.class)
    public void UnknownAccountException(HttpServletRequest request,
                                        Exception exception,
                                        HttpServletResponse response) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
//        return new ModelAndView("/403");
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            ResponseResult result = new ResponseResult();
            result.setSuccess(false);
//            result.setMessage(new String("登录超时,请从新登录".getBytes("UTF-8"), "UTF-8"));
            result.setMessage(new String(exception.getMessage().getBytes("UTF-8"), "UTF-8"));

            ObjectMapper objectMapper = new ObjectMapper();
//            JsonGenerator jsonGenerator = objectMapper.getFactory()
//                    .createGenerator(System.out, JsonEncoding.UTF8);
            //对象转JSON
            String json = objectMapper.writeValueAsString(result);//返回字符串，输出
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().append(json);
        } else {
//            非ajax请求
            response.sendRedirect("/index");
        }
    }


    /**
     * 类型强制转换错误
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ClassCastException.class)
    public ResponseResult<String> ClassCastException(HttpServletRequest request,
                                                     Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("类型强制转换错误，请确认数据准确性");
        return result;
    }


    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public ResponseResult<String> ArrayIndexOutOfBoundsException(HttpServletRequest request,
                                                                 Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数组下标越界错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseResult<String> FileNotFoundException(HttpServletRequest request,
                                                        Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("文件未找到错误");
        return result;
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseResult<String> SQLException(HttpServletRequest request,
                                               Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("操作数据库错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseResult<String> IOException(HttpServletRequest request,
                                              Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("输入输出错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = NoSuchMethodException.class)
    public ResponseResult<String> NoSuchMethodException(HttpServletRequest request,
                                                        Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("方法未找到错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = OutOfMemoryError.class)
    public ResponseResult<String> OutOfMemoryError(HttpServletRequest request,
                                                   Exception exception) throws Exception {
//        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("内存不足错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = StackOverflowError.class)
    public ResponseResult<String> StackOverflowError(HttpServletRequest request,
                                                     Exception exception) throws Exception {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("堆栈溢出错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = VirtualMachineError.class)
    public ResponseResult<String> VirtualMachineError(HttpServletRequest request,
                                                      Exception exception) throws Exception {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("虚拟机错误，请确认数据准确性");
        return result;
    }

//    @ExceptionHandler(value = JDBCException.class)
//    @ResponseBody
//    public String JDBCException(HttpServletRequest request,
//                                Exception exception) throws Exception {
//        //exception.printStackTrace();
//        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getSuppressed() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
//        log.debug("ERROR::::：" + exception.getStackTrace() + "::::::" + new Date());
//        return "JDBCException，请确认数据准确性";
//    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseResult<String> nullPointerException(HttpServletRequest request,
                                                       Exception exception) throws Exception {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("空指针错误，请确认数据准确性");
        return result;
    }

    @ExceptionHandler(value = org.xml.sax.SAXException.class)
    public ResponseResult<String> SAXException(HttpServletRequest request,
                                               Exception exception) throws Exception {
        //exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("页面存在多个<!DOCTYPE html>");
        return result;
    }

    @ExceptionHandler(value = org.xml.sax.SAXParseException.class)
    public ResponseResult<String> SAXParseException(HttpServletRequest request,
                                                    Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("class 重复");
        return result;
    }

    @ExceptionHandler(value = CannotGetJdbcConnectionException.class)
    public ResponseResult<String> CannotGetJdbcConnectionException(HttpServletRequest request,
                                                                   Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("数据库链接错误");
        return result;
    }

    @ExceptionHandler(value = TemplateInputException.class)
    public ResponseResult<String> templateInputException(HttpServletRequest request,
                                                         Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("页面书写错误");
        return result;
    }

    @ExceptionHandler(value = TemplateProcessingException.class)
    public ResponseResult<String> templateProcessingException(HttpServletRequest request,
                                                              Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage("页面书写错误");
        return result;
    }


    /**
     * 未知异常
     * 此方法最好放最底部
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public void exception(HttpServletRequest request,
                          HttpServletResponse response,
                          Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
//        if (isAjax(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            ResponseResult<String> result = new ResponseResult<>(false, "未知异常");
            response.getWriter().write(Objects.requireNonNull(JackJson.beanToJson(result)));
//            httpServletResponse.sendRedirect("/index");
//        } else {
            //saveRequestAndRedirectToLogin(request, response);
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
//            response.sendRedirect("/views/error/500");
//        }
    }
}
