package com.jinhaoxun.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
//定时任务
@EnableScheduling
//listener自动注册
@ServletComponentScan
//扫描dubbo服务包
@EnableDubbo
@EnableCaching
@MapperScan({"com.jinhaoxun.dubbo.mapper.quartz","com.jinhaoxun.dubbo.mapper.user"})
@SpringBootApplication
public class DubboServiceManagerApplication {
    public static void main(String[] args) {
        log.info("======================== 迈叽叽歪歪，担猛猛开始！========================");
        SpringApplication.run(DubboServiceManagerApplication.class, args);
        log.info("========================= 兴死有神秀，无BUG哩！========================");
    }
}
