package com.lmfun.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @Descriptions:
 * @Author: 蘑小菇
 * @Date : 2018-03-06 15:07
 */
@Configuration
public class StaticVariableConfig {

    /**
     * thymleaf 全局变量定义
     * @param viewResolver
     */
    @Autowired
    public void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver,Environment environment) {
        if(viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("QINIU_BUCKET", environment.getProperty("qiniu.bucket.img"));
            vars.put("QINIU_DOMAIN", environment.getProperty("qiniu.domain"));
            viewResolver.setStaticVariables(vars);
        }
    }
}
