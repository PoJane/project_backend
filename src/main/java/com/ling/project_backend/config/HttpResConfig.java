package com.ling.project_backend.config;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.nio.charset.StandardCharsets;

@Configuration
public class HttpResConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dir="file:"+new ApplicationHome().getDir().getAbsolutePath()+"/src/main/resources/static/images/";
        registry.addResourceHandler("/images/**") //url
                .addResourceLocations(dir); //路径
    }

    //文件名称字符编码
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper=new UrlPathHelper();
        urlPathHelper.setUrlDecode(false);
        urlPathHelper.setDefaultEncoding(StandardCharsets.UTF_8.name());
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
