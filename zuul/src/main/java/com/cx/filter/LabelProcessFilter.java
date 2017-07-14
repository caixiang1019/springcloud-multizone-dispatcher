package com.cx.filter;

import com.cx.core.CoreHeaderWebInterceptor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;

/**
 * Created by caixiang on 2017/7/10.
 */
public class LabelProcessFilter extends ZuulFilter {
    private static final HashMap<String, String> TOKEN_LABEL_MAP = new HashMap<>();

    static {
        TOKEN_LABEL_MAP.put("sh", "sh");
        TOKEN_LABEL_MAP.put("bj", "bj");
        TOKEN_LABEL_MAP.put("tj", "tj");
        TOKEN_LABEL_MAP.put("gz", "gz");
    }

    private static final Logger logger = LoggerFactory.getLogger(LabelProcessFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = ctx.getRequest().getHeader(HttpHeaders.AUTHORIZATION);

        String labels = TOKEN_LABEL_MAP.get(token);
        String t = Thread.currentThread().getName();
        logger.info("label: " + labels);

        // zuul初始化label
        CoreHeaderWebInterceptor.initThreadLocalContext(labels);
        // header传递给后续微服务
        ctx.addZuulRequestHeader(CoreHeaderWebInterceptor.HEADER_LABEL, labels);

        return null;
    }
}
