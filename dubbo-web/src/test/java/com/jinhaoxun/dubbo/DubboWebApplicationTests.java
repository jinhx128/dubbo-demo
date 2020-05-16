package com.jinhaoxun.dubbo;

import com.jinhaoxun.dubbo.util.encodeutil.EncodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest
class DubboWebApplicationTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

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
    void test2() {
        redisTemplate.opsForValue().set("测试1","哈哈");
        System.out.println(redisTemplate.opsForValue().get("测试1"));
    }

    @Test
    void test3() throws Exception{
        log.info(EncodeUtil.encoderBySHA256("ABCabc123!"));
    }

    @Test
    void test5() throws Exception{
        taskExecutor.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + "-" +i);
            }
        });
        taskExecutor.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + "-" +i);
            }
        });
        System.out.println("主线程输出");
    }

    @Test
    void test4() {
        String name1 = "测试字段1";
        String name2 = "测试字段2222222222222";
        String miyao1 = "123";
        String miyao2 = "abcdef";
        String s1 = EncodeUtil.encryptByAES(name1, miyao1);
        log.info(s1);
        String s2 = EncodeUtil.encryptByAES(name1, miyao2);
        log.info(s2);
        String s3 = EncodeUtil.encryptByAES(name2, miyao1);
        log.info(s3);
        String s4 = EncodeUtil.encryptByAES(name2, miyao2);
        log.info(s4);


        String s5 = EncodeUtil.decryptByAES(s1, miyao1);
        String s6 = EncodeUtil.decryptByAES(s2, miyao2);
        log.info(s5);
        log.info(s6);

    }

    @Test
    void test6() {
        String srs = "hDsEapcnULQusfMXTT4SPnvdLgmJZEJOSRjEGuRRaeGNZgQToGIn2v7LBaxyZHqFUEzcWweA/JlWWfUnSCNOrs+3OAh2qtZZJHKEOb1Qv/EQ+BdR8UhFEVV16QsAJUiR6mQAMyAxsLGAuMu4n0Hqp6wDkwiMrMNsLgFr/tjHgmBoAFWI3U/wvodYpfNvY3/5J/HD+1D6+wglu3kXomDdmNwBjMYpOq7zHitOyFqT5BuOMmEjzmBFmGgH6alPkWRKHiJ6xRUqz7P85qO80UReyTMCbpaDLkjgw0mJVgmcFYjm9c4FcJSuF6raiTt/gFM2G9FxJ0U5Ca0A0rPfE5t42ULeAu0tHDJCc6EcirUbb6w8CFnAOookYkWdgJ1WgbaLsoce43ZW4srQeDjJgk7EsQ==";
        System.out.println(EncodeUtil.decryptByAES(srs, "key"));
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
