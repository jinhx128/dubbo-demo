package com.jinhaoxun.dubbo.org.listener;

import com.jinhaoxun.dubbo.exception.CustomRuntimeException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description 启动项目初始化数据监听器
 */
@Slf4j
@WebListener
public class InitWebListener implements ServletContextListener {

    /**
     * @author jinhaoxun
     * @description 上下文初始化时操作
     * @param sce
     * @throws CustomRuntimeException
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("开始初始化Web上下文...");
        log.info("初始化Web上下文成功！");
    }

    /**
     * @author jinhaoxun
     * @description 上下文销毁时操作
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
