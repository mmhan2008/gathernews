package com.cnstock.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * description:监听器配置
 * www.cnstock.com
 * Created by rrz on 2018/1/22.
 */
public class InitListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(InitListener.class);

    /**
     * 初始化数据
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("初始化数据开始");
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());



    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("初始化数据结束");
    }

}
