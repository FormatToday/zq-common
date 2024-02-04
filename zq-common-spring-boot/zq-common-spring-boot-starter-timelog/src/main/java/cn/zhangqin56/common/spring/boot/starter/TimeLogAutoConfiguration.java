package cn.zhangqin56.common.spring.boot.starter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnProperty(
        prefix = "time.log",
        name = "enable",
        havingValue = "true",
        matchIfMissing = true
)
public class TimeLogAutoConfiguration {

    @SneakyThrows
    @Around("@annotation(cn.zhangqin56.common.spring.boot.starter.annotation.TimeLog)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        String methodName = proceedingJoinPoint.getSignature().toLongString().split(" ")[2];
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(methodName);
        Object res = proceedingJoinPoint.proceed();
        stopWatch.stop();
        logger.info("【{}ms】方法：{}", stopWatch.getTotalTimeMillis(), methodName);
        return res;
    }
}
