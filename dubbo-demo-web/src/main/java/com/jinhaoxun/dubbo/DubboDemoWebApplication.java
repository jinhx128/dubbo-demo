package com.jinhaoxun.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableAsync
@EnableScheduling
@ServletComponentScan
//扫描dubbo服务包
@EnableDubbo
//开启服务容错
@EnableHystrix
@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DubboDemoWebApplication {
    public static void main(String[] args) {
        log.info("========================迈叽叽歪歪，担猛猛开始！========================");
        SpringApplication.run(DubboDemoWebApplication.class, args);
        log.info("=========================兴死有神秀，无BUG哩！========================");
    }
}