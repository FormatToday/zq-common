package cn.zhangqin56.common.spring.boot.starter.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "zq.request.log")

public final class RequestLogProperties {
    private Boolean enable;
    private List<String> blackList;
}
