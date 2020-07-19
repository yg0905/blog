package com.yg.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    //规定这是个切面 然后通过切面来拦截哪个包下的类中的方法
    @Pointcut("execution(* com.yg.blog.controller.*.*(..))")
    public void log(){

    }
    //方法之前
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //拿到http对象
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //url
        String url = request.getRequestURL().toString();
        //获取方法名
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        //ip
        String remoteAddr = request.getRemoteAddr();
        //值
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,remoteAddr,classMethod,args);
        logger.info("request:{}",requestLog);
    }
    //方法之后记录日志
    @After("log()")
    public void doAfter(){
        logger.info("==========doAfter===========");
    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("Resilt:{}"+result);
    }
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object aces;

        public RequestLog(String url, String ip, String classMethod, Object aces) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.aces = aces;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", aces=" + aces +
                    '}';
        }
    }
}

