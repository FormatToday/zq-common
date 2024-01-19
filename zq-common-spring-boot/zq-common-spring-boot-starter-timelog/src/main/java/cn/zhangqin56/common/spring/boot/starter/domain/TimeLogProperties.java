package cn.zhangqin56.common.spring.boot.starter.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "zq.time.log")
public class TimeLogProperties {
    private Boolean enable;
}
