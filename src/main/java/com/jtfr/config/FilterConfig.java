package com.jtfr.config;

import com.jtfr.filter.LogbackRequestLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Filter 扫描类
 */
@Configuration
public class FilterConfig {

    @Bean(name = "logbackRequestLoggingFilter")
    public LogbackRequestLoggingFilter loggingFilter() {
        LogbackRequestLoggingFilter logbackRequestLoggingFilter = new LogbackRequestLoggingFilter();
        return logbackRequestLoggingFilter;
    }

}