package com.example.reggie.config;

import com.example.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration //需要这个注解，配置才能生效
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 按/**再加回车即可生成这种注释
     * 设置静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射...");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     *@Description 扩展MVC框架的消息转换器
     *@Author chenxu
     *@Date 2022/5/30 22:08
     **/
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        //创建消息转换器
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        converter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到MVC框架的转换器集合中
        converters.add(0,converter);
    }
}
