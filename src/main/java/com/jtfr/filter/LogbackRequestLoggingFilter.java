package com.jtfr.filter;

import com.jtfr.util.FilterOrders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Order(FilterOrders.ZERO)
public class LogbackRequestLoggingFilter extends AbstractRequestLoggingFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(LogbackRequestLoggingFilter.class);

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

        MDC.put("requestId", UUID.randomUUID().toString());
        LOGGER.info("requestId = {}", MDC.get("requestId"));

    }


    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        // 清理本线程缓存，防止干扰下次请求
        MDC.clear();
    }

}