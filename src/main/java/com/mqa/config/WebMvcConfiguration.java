package com.mqa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    /**
     * 拓展Spring MVC的消息转化器，用于将java对象序列化为json字符串（显示时间戳）
     *
     * @param converters
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        log.info("拓展Spring MVC的消息转化器...");
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //为消息转换器设置对象转换器，对象转换器可用将java对象序列化为json字符串
        converter.setObjectMapper(new JacksonObjectMapper());
        //将消息转换器添加到容器中
        converters.add(0, converter);

        log.info("拓展Spring MVC的消息转化器完成！\n");
    }

    public void addCorsMappings(CorsRegistry registry) {
        log.info("拓展Spring MVC的跨域配置...");
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 允许发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");

        log.info("拓展Spring MVC的跨域配置完成！\n");
    }
    //@Override
    //public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //    registry.addResourceHandler("/**").addResourceLocations(
    //            "classpath:/static/");
    //    registry.addResourceHandler("swagger-ui.html").addResourceLocations(
    //            "classpath:/META-INF/resources/");
    //    registry.addResourceHandler("/webjars/**").addResourceLocations(
    //            "classpath:/META-INF/resources/webjars/");
    //    super.addResourceHandlers(registry);
    //}
}
