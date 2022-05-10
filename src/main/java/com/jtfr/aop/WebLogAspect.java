package com.jtfr.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Aop 配置参数
 * 1. spring.aop.auto=true # 默认true：开启@EnableAspectJAutoProxy.
 * 2. spring.aop.proxy-target-class=false # 默认 true 使用CGLIB
 */
@Aspect
@Configuration
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("@within( org.springframework.stereotype.Controller)")
    private void controller() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    private void restController() {
    }


    @Pointcut("controller() || restController()")
    private void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        final Signature signature = joinPoint.getSignature();
        // 打印执行的方法，类全路径名 + "." + 方法名。案例：Execute method: com.jtfr.controller.TestController.post
        logger.debug("Execute method: {}", (signature.getDeclaringTypeName() + "." + signature.getName()));

    }

    @AfterReturning(pointcut = "webLog()", returning = "obj")
    public void doAfterReturning(Object obj) {
        // 打印响应结果
        logger.info("Object Response: {}", JSON.toJSONString(obj));
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void doRecoveryActions(Exception e) {
        // 异常返回
        String errorMsg = e.toString();
        // 此处用 warn 警示具体的error在 ControllerExceptionHandler 处理并记录
        logger.error("Exception Response: {}", e.toString());
    }
}