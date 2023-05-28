package com.khangnlg.config;

import com.khangnlg.interceptor.GlobalHandlerRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebMVCConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private GlobalHandlerRequestInterceptor globalHandlerRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalHandlerRequestInterceptor);
    }

}
