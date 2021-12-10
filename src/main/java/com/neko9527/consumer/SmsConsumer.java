package com.neko9527.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxuechao
 * @date 2021-12-10
 */
@Configuration
@RabbitListener(queues = "sms-queue")
public class SmsConsumer {

    @RabbitHandler
    public void smsMessageHandler(String message) {
        System.out.println("短信服务接收到消息: " + message);
    }
}
