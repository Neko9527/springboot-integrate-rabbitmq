package com.neko9527.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxuechao
 * @date 2021-12-10
 */
@Configuration
@RabbitListener(queues = "email-queue")
public class EmailConsumer {

    @RabbitHandler
    public void emailMessageHandler(String message) {
        System.out.println("邮件服务接收到消息: " + message);
    }
}
