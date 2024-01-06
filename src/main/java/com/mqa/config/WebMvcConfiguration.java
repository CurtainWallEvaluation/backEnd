package com.mqa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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

}
