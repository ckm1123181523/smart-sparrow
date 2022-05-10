package com.jtfr.config;

import com.jtfr.filter.LogbackRequestLoggingFilter;
import com.jtfr.filter.ParameterWrapperFilter;
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

    @Bean(name = "parameterWrapperFilter")
    public ParameterWrapperFilter parameterWrapperFilter() {
        ParameterWrapperFilter parameterWrapperFilter = new ParameterWrapperFilter();
        return parameterWrapperFilter;
    }
}