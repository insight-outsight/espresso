package org.ootb.espresso.springcloud.infrastructure;

import org.ootb.espresso.springcloud.infrastructure.enums.StringCodeToEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringCodeToEnumConverterFactory());
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MDCCleanInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/register")
        .excludePathPatterns("/login");
        registry.addInterceptor(new MDCInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/register")
        .excludePathPatterns("/login");
    }

}