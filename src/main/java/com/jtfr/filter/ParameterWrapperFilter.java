package com.jtfr.filter;

import com.alibaba.fastjson.JSON;
import com.jtfr.util.FilterOrders;
import com.jtfr.wrapper.ParameterRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Order(FilterOrders.ONE)
public class ParameterWrapperFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(ParameterWrapperFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 打印 url ？ 后面的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        LOGGER.info("requestParameterMap = {}", JSON.toJSONString(parameterMap));

        // Post 请求打印请求体参数，其他请求暂时不处理。
        if (StringUtils.equalsIgnoreCase(request.getMethod(), HttpMethod.POST.name())) {
            // 打印请求参数
            StringWriter writer = new StringWriter();
            IOUtils.copy(request.getInputStream(), writer, StandardCharsets.UTF_8.name());
            String requestDataStr = writer.toString();
            LOGGER.info("requestDataStr: {}", requestDataStr);
            /**
             * 1. HttpServletRequest 的  inputStream 读取是一次性的，最后还要我们自己写入进去。
             */
            ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request, requestDataStr);
            filterChain.doFilter(requestWrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}