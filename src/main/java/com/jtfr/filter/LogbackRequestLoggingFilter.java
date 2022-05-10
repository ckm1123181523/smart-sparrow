package com.jtfr.filter;

import com.jtfr.util.FilterOrders;
import com.jtfr.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Order(FilterOrders.ZERO)
public class LogbackRequestLoggingFilter extends AbstractRequestLoggingFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(LogbackRequestLoggingFilter.class);

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

        MDC.put("requestId", UUID.randomUUID().toString());

        // message 是请求的 url 。案例：Before request [GET /test/get]
        LOGGER.info(message);

        // 设置开始时间
        LogUtil.startTime();

        // 案例：Request Message RemoteIp: 127.0.0.1, AcceptGzip:  gzip, deflate, br
        LOGGER.info("Request Message RemoteIp: {}, AcceptGzip: {}", LogUtil.getRemoteIp(request), request.getHeader(HttpHeaders.ACCEPT_ENCODING));

    }


    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

        // 打印请求 url 和 请求耗时 案例： After request [POST /test/post], http request elapsed time: 134 ms
        LOGGER.info("{}, http request elapsed time: {} ms.", message, LogUtil.elapsedTime());
        // 清理本线程缓存，防止干扰下次请求
        MDC.clear();

    }

}