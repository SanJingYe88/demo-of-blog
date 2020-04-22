package it.com.demo.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerAop {

    //重用切入点表达式
    @Pointcut(value = "execution(public * it.com.rest.controller.UserController.*(..))")
    public void controllerPointCut() {
    }

    //前置通知
    @Before(value = "controllerPointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        log.info("调用了ControllerAop的前置通知");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("请求的URL:{}", request.getRequestURL().toString());
        log.info("请求的HTTP_METHOD:{}", request.getMethod());
        log.info("请求的IP:{}", request.getRemoteAddr());
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("当前调用的类为:{}", typeName);
        String methodName = joinPoint.getSignature().getName();
        log.info("当前调用的方法为:{}", methodName);
        Object[] objects = joinPoint.getArgs();
        log.info("方法的请求参数为:{}", Arrays.asList(objects));
    }

    //后置通知
    @AfterReturning(value = "controllerPointCut()", returning = "result")
    public void afterMethod(JoinPoint joinPoint, Object result) {
        log.info("调用了ControllerAop的返回通知");
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("当前调用的类为:{}", typeName);
        String methodName = joinPoint.getSignature().getName();
        log.info("当前调用的方法为:{}", methodName);
        log.info("当前方法的返回值为:{}", result);
    }

    //异常通知
    @AfterThrowing(value = "controllerPointCut()", throwing = "ex")
    public void throwing(JoinPoint joinPoint, Exception ex) {
        log.info("调用了ControllerAop的异常通知");
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("当前调用的类为:{}", typeName);
        String methodName = joinPoint.getSignature().getName();
        log.info("当前调用的方法为:{}", methodName);
        log.info("抛出的异常为:{}", ex.getMessage());
    }
}
