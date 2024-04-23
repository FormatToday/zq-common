package cn.zhangqin56.common.spring.boot.starter.config;

import cn.zhangqin56.common.spring.boot.starter.domain.RequestLogProperties;
import cn.zhangqin56.common.spring.boot.starter.interceptor.RequestLogInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
public class WebInterceptorConfiguration implements WebMvcConfigurer {
    private final RequestLogProperties requestLogProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLogInterceptor(requestLogProperties));
    }
}