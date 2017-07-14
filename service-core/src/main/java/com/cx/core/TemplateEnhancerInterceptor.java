package com.cx.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

/**
 * Created by caixiang on 2017/7/9.
 */
public class TemplateEnhancerInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TemplateEnhancerInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);

        String header = CoreHeaderWebInterceptor.label.get();
        logger.info("label: "+header);
        requestWrapper.getHeaders().add(CoreHeaderWebInterceptor.HEADER_LABEL, header);

        return execution.execute(requestWrapper, body);
    }
}
