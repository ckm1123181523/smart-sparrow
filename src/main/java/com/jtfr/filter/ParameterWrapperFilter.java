package com.jtfr.filter;

import com.jtfr.servlet.ParameterRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * 作用：打印入参到日志
 * @Order 注解，指明 filter 处理优先级，数字小的优先处理。
 */
@Order(2)
public class ParameterWrapperFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(ParameterWrapperFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 打印请求参数
        StringWriter writer = new StringWriter();
        IOUtils.copy(request.getInputStream(), writer, StandardCharsets.UTF_8.name());
        String requestDataStr = writer.toString();
        LOGGER.info("requestDataStr: {}", requestDataStr);

        /**
         * 1. HttpServletRequest 的  inputStream 读取是一次性的，最后还要我们自己写入进去。
         * 2. 打印响应结果，在 WebLogAspect
         */
        ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request, requestDataStr);

        filterChain.doFilter(requestWrapper, response);
    }
}
