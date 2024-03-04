package cn.zhangqin56.common.spring.boot.starter.config;

import cn.zhangqin56.common.spring.boot.starter.domain.RequestLogProperties;
import cn.zhangqin56.common.spring.boot.starter.filter.RequestLogFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@ConditionalOnProperty(
        prefix = "zq.request.log",
        name = "enable",
        havingValue = "true",
        matchIfMissing = true
)
@RequiredArgsConstructor
@ComponentScan("cn.zhangqin56.common.spring.boot.starter")
@EnableConfigurationProperties(RequestLogProperties.class)
public class WebFilterConfiguration {
    private final RequestLogProperties requestLogProperties;

    @Bean
    public FilterRegistrationBean<RequestLogFilter> requestLogFilter() {
        FilterRegistrationBean<RequestLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLogFilter(requestLogProperties));
        // 拦截 "/api" 开头的请求
        registrationBean.addUrlPatterns("/*");
        // 执行顺序最靠前
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}