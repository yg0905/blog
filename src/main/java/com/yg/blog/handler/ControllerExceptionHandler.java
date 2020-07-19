package com.yg.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截带有注解Controller的类
@ControllerAdvice
public class ControllerExceptionHandler {
//    拦截器
    //获得日志
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    //表示该方法可以做异常处理 括号里面意思是 只要是Exception级别的都可以
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e)throws Exception{

        //记录异常信息
        logger.error("Request URL: {} Exception : {} ",request.getRequestURL(),e);
        if (AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }

}
