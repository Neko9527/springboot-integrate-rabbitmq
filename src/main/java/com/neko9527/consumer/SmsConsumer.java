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
public class SmsConsumer {

    @RabbitHandler
    @RabbitListener(queues = "sms-queue")
    public void smsMessageHandler(Message message) {
        System.out.println("短信服务接收到消息: " + new String(message.getBody()));
    }
}
