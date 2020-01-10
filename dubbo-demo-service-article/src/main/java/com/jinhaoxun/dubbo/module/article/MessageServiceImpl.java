package com.jinhaoxun.dubbo.module.article;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhaoxun.dubbo.pojo.article.Message;
import com.jinhaoxun.dubbo.mapper.article.MessageMapper;
import com.jinhaoxun.dubbo.module.article.service.MessageService;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2018-05-09
 * @description 消息服务类
 */
@Service
@Component
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}