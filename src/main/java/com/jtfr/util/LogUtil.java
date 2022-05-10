package com.jtfr.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志相关工具类
 */
public class LogUtil {

    private static final String LOCALHOST_127 = System.getProperty("application.ip");

    public static void startTime() {
        MDC.put("startTime", String.valueOf(System.currentTimeMillis()));
    }

    public static long elapsedTime() {
        return System.currentTimeMillis() - Long.valueOf(MDC.get("startTime"));
    }

    public static Object getRemoteIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        } else {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
                ip = ip.substring(0, ip.indexOf(44));
            }

            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = LOCALHOST_127;
            }

            return StringUtils.isNotBlank(ip) ? ip : LOCALHOST_127;
        }
    }
}