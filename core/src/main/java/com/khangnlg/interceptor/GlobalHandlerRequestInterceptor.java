package com.khangnlg.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalHandlerRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return request.getRequestURI().startsWith("/api/v1")
                || request.getRequestURI().startsWith("/swagger")
                || request.getRequestURI().startsWith("/configuration")
                || request.getRequestURI().startsWith("/v2/api-docs")
                || request.getRequestURI().startsWith("/webjars");
    }

}
