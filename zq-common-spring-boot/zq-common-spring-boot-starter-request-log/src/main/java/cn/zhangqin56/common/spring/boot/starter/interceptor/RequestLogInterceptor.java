package cn.zhangqin56.common.spring.boot.starter.interceptor;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.zhangqin56.common.spring.boot.starter.domain.RequestLogProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.util.List;

/**
 * 记录请求日志
 */
@Slf4j
public class RequestLogInterceptor implements HandlerInterceptor {
    private final List<String> blackList;

    public RequestLogInterceptor(RequestLogProperties requestLogProperties) {
        this.blackList = requestLogProperties.getBlackList();
        logger.debug("blackList:{}", blackList);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!shouldSkip(request)) {
            // Wrapper 封装 Request 和 Response
            ContentCachingRequestWrapper cachingRequest = new ContentCachingRequestWrapper(request);
            ContentCachingResponseWrapper cachingResponse = new ContentCachingResponseWrapper(response);

            // 在请求完成后记录请求、响应日志
            // 请求方法
            String method = request.getMethod();
            // URI
            String uri = request.getRequestURI();
            // 请求体
            byte[] requestContent = cachingRequest.getContentAsByteArray();


            String id = IdUtil.nanoId();
            logger.info("\nRequest[{}] ==> {} {}\n{}", id, method, uri, JSONUtil.formatJsonStr(new String(requestContent)));
            // 响应状态
            int status = response.getStatus();
            // 响应体
            byte[] responseContent = cachingResponse.getContentAsByteArray();

            logger.info("\nResponse[{}] <== {}\n{}", id, status, JSONUtil.formatJsonStr(new String(responseContent)));

            // 把缓存的响应数据，响应给客户端
            cachingResponse.copyBodyToResponse();
        }
        // 继续执行请求链
        return true;
    }

    private boolean shouldSkip(HttpServletRequest request) {
        if (null == blackList || blackList.isEmpty()) {
            return false;
        }
        return blackList.stream()
                .anyMatch(uri -> request.getRequestURI().startsWith(uri));
    }
}
