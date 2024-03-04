package cn.zhangqin56.common.spring.boot.starter.filter;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.zhangqin56.common.spring.boot.starter.domain.RequestLogProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 记录请求日志
 */
@Slf4j
public class RequestLogFilter extends HttpFilter {
    private final List<String> blackList;

    public RequestLogFilter(RequestLogProperties requestLogProperties) {
        this.blackList = requestLogProperties.getBlackList();
        logger.debug("blackList:{}", blackList);
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (shouldSkip(request)) {
            // 不需要记录请求体和响应体
            chain.doFilter(request, response);
        } else {
            // Wrapper 封装 Request 和 Response
            ContentCachingRequestWrapper cachingRequest = new ContentCachingRequestWrapper(request);
            ContentCachingResponseWrapper cachingResponse = new ContentCachingResponseWrapper(response);

            // 继续执行请求链
            chain.doFilter(cachingRequest, cachingResponse);

            // 在请求完成后记录请求、响应日志
            // 请求方法
            String method = request.getMethod();
            // URI
            String uri = request.getRequestURI();
            // 请求体
            byte[] requestContent = cachingRequest.getContentAsByteArray();


            String id = IdUtil.nanoId();
            logger.info("\nRequest[{}] => {} {}\n{}", id, method, uri, JSONUtil.formatJsonStr(new String(requestContent)));
            // 响应状态
            int status = response.getStatus();
            // 响应体
            byte[] responseContent = cachingResponse.getContentAsByteArray();

            logger.info("\nResponse[{}] <= {}\n{}", id, status, JSONUtil.formatJsonStr(new String(responseContent)));

            // 把缓存的响应数据，响应给客户端
            cachingResponse.copyBodyToResponse();
        }

    }

    private boolean shouldSkip(HttpServletRequest request) {
        if (null == blackList || blackList.isEmpty()) {
            return false;
        }
        return blackList.stream()
                .anyMatch(uri -> request.getRequestURI().startsWith(uri));
    }
}
