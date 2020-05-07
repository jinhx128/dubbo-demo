package com.jinhaoxun.dubbo;

import com.jinhaoxun.dubbo.pojo.article.Article;
import com.jinhaoxun.dubbo.pojo.article.Message;
import com.jinhaoxun.dubbo.util.encodeutil.EncodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest
class DubboDemoWebApplicationTests {

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
    @Async("taskExecutor")
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
    void testJava8() throws Exception{
//        List<Article> list = Arrays.asList(
//                new Article(),new Article(),new Article()
//        );

        List<Message> list = new ArrayList<>();
        Message message = new Message();
        message.setContent("Apple");
        message.setCreateTime(new Date());
        message.setMessageId(444L);
        message.setUserId(123L);
        list.add(message);

        Message message2 = new Message();
        message2.setContent("Dog");
        message2.setCreateTime(new Date());
        message2.setMessageId(222L);
        message2.setUserId(123L);
        list.add(message2);

        Message message3 = new Message();
        message3.setContent("Banana");
        message3.setCreateTime(new Date());
        message3.setMessageId(333L);
        message3.setUserId(456L);
        list.add(message3);

        Message message4 = new Message();
        message4.setContent("Watermelon");
        message4.setCreateTime(new Date());
        message4.setMessageId(444L);
        message4.setUserId(456L);
        list.add(message4);

        Integer i = list.stream()
                .map(x -> 1)
                .reduce(Integer::sum)
                .get();
        log.info(i.toString());

        log.info("----------------------------------");

        list.stream()
                .filter(x -> x.getUserId() == 123L)
                .sorted(Comparator.comparingLong(Message::getMessageId))
                .map(Message::getContent)
                .forEach(System.out::println);

        log.info("----------------------------------");

        list.stream()
                .map(Message::getUserId)
                .distinct()
                .forEach(System.out::println);

        log.info("----------------------------------");

        String s = list.stream()
                .map(Message::getContent)
                .sorted()
                .reduce(String::concat)
                .get();
        log.info(s);

        log.info("----------------------------------");

        String s1 = list.stream()
                .map(Message::getContent)
                .flatMap(x-> Arrays.stream(x.split("")))
                .sorted(String::compareToIgnoreCase)
                .reduce(String::concat)
                .get();
        log.info(s1);

        log.info("----------------------------------");

        boolean b = list.stream()
                .anyMatch(x -> x.getUserId() == 123L);
        log.info(String.valueOf(b));

        log.info("----------------------------------");

        Long l = list.stream()
                .map(Message::getMessageId)
                .parallel()//并行流
                .max(Long::compare)
                .get();
        log.info(String.valueOf(l));
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


    @BeforeEach
    void testBefore(){
        log.info("测试开始!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @AfterEach
    void testAfter(){
        log.info("测试结束!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

}
