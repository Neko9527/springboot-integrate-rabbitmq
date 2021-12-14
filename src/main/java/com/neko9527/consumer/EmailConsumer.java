package com.neko9527.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxuechao
 * @date 2021-12-10
 */
@Configuration
public class EmailConsumer {

    @RabbitHandler
    @RabbitListener(queues = "email-queue")
    public void emailMessageHandler(Message message) {
        System.out.println("邮件服务接收到消息: " + new String(message.getBody()));
    }
}
