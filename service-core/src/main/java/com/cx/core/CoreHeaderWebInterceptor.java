package com.cx.core;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by caixiang on 2017/7/9.
 */
public class CoreHeaderWebInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CoreHeaderWebInterceptor.class);

    public static final String HEADER_LABEL = "x-label";

    public static final ThreadLocal<String> label = new ThreadLocal<>();


    public static void initThreadLocalContext(String labels) {
        logger.info("label: " + labels);
        if (!StringUtils.isEmpty(labels)) {
            CoreHeaderWebInterceptor.label.set(labels);
        } else {
            CoreHeaderWebInterceptor.label.set(null);
        }
    }

    public static void clearThreadLocalContext() {
        if (!StringUtils.isEmpty(CoreHeaderWebInterceptor.label.get())) {
            CoreHeaderWebInterceptor.label.remove();
        }
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CoreHeaderWebInterceptor.initThreadLocalContext(request.getHeader(CoreHeaderWebInterceptor.HEADER_LABEL));
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        CoreHeaderWebInterceptor.clearThreadLocalContext();
    }

}
