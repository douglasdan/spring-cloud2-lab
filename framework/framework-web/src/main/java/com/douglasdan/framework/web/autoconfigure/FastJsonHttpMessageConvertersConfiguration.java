package com.douglasdan.framework.web.autoconfigure;

import com.alibaba.fastjson.JSONObject;
import com.douglasdan.framework.web.controller.converter.FastJsonHttpGenericMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 *
 * @author Douglas.Dan
 * @date 2018-08-21
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({HttpMessageConverter.class, JSONObject.class})
public class FastJsonHttpMessageConvertersConfiguration {

    @Bean
    public FastJsonHttpGenericMessageConverter fastJsonHttpGenericMessageConverter() {
        return new FastJsonHttpGenericMessageConverter();
    }

}
