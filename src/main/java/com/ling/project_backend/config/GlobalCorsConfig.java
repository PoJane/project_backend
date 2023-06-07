package com.ling.project_backend.config;

/**
 * 处理跨域访问问题
 */
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")    //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
                        .allowedOriginPatterns("*")    //开放哪些ip、端口、域名的访问权限 SpringBoot2.4.0以后allowedOrigins被allowedOriginPatterns代替
                        .allowCredentials(true)  //是否允许发送Cookie信息
                        .allowedMethods("GET", "POST", "PUT", "DELETE")     //开放哪些Http方法，允许跨域访问
                        .allowedHeaders("*")     //允许HTTP请求中的携带哪些Header信息
                        .exposedHeaders("*");   //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
            }
        };
    }

    /**
     * 跨域配置
     * @return
     */
    /*
    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter();
    }
    */
}
