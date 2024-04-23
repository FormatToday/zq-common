package cn.zhangqin56.common.spring.boot.starter.config;

import cn.zhangqin56.common.spring.boot.starter.domain.TimeLogProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnProperty(
        prefix = "zq.time.log",
        name = "enable",
        havingValue = "true",
        matchIfMissing = true
)
@EnableConfigurationProperties({TimeLogProperties.class})
@RequiredArgsConstructor
public class TimeLogAutoConfiguration {
    private final TimeLogProperties properties;

    @SneakyThrows
    @Around("@annotation(cn.zhangqin56.common.spring.boot.starter.annotation.TimeLog)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        String methodName = proceedingJoinPoint.getSignature().toLongString().split(" ")[2];
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(methodName);
        Object res = proceedingJoinPoint.proceed();
        stopWatch.stop();
        String logTemplate = properties.getLogTemplate();
        if (logTemplate != null && !logTemplate.isEmpty()) {
            logger.info("耗时：{}ms\t{}", stopWatch.getTotalTimeMillis(), logTemplate);
        } else {
            logger.info("耗时：{}ms\t方法：{}", stopWatch.getTotalTimeMillis(), methodName);
        }
        return res;
    }
}
