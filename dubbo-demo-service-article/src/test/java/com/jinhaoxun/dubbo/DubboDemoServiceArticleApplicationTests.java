package com.jinhaoxun.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest
class DubboDemoServiceArticleApplicationTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void test() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String dateString = formatter.format(date);
        log.info(dateString);
    }

    @Test
    void test1() {
        // 10位的秒级别的时间戳
        long time1 = 1527767665;
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time1 * 1000));
        System.out.println("10位数的时间戳（秒）--->Date:" + result1);
        Date date1 = new Date(time1*1000);   //对应的就是时间戳对应的Date
        // 13位的秒级别的时间戳
        String time2 = "1515730332000d";
        String result2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Double.valueOf(time2));
        System.out.println(result2);
    }

    @Test
    @Async("taskExecutor")
    void test2() {
        redisTemplate.opsForValue().set("测试1","哈哈");
        System.out.println(redisTemplate.opsForValue().get("测试1"));
    }

    @BeforeEach
    void testBefore(){
        log.info("测试开始!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @AfterEach
    void testAfter(){
        log.info("测试结束!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

}
