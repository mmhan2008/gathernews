package com.cnstock.configuration;

import com.cnstock.listener.InitListener;
import com.cnstock.service.IndexServiceImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * description:监听器注册配置类
 * www.cnstock.com
 * Created by rrz on 2018/1/22.
 */
@SpringBootConfiguration
public class ListenerConfiguration {

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean =
                new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }

}
