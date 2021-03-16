package com.neko9527.config;

import com.neko9527.common.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxuechao
 * @date 2021-03-15
 */
@Configuration
public class RabbitMqConfig {


    /**
     * 使用TTL(Time-To-Live)方式实现延时队列
     * @param amqpAdmin
     */
    public RabbitMqConfig(AmqpAdmin amqpAdmin) {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-delayed-type", "direct");
        //属性参数 交换机名称 交换机类型 是否持久化 是否自动删除 配置参数
        amqpAdmin.declareExchange(new CustomExchange(Constants.MqConstants.delayExChange, ExchangeTypes.DIRECT, true, false, null));

        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 10000); //消息存活 10s
        map.put("x-dead-letter-exchange", Constants.MqConstants.delayExChange);
        //当消息过期后发送到普通队列中
        map.put("x-dead-letter-routing-key", Constants.MqConstants.commonKey);
        //创建一个延时队列
        amqpAdmin.declareQueue(new Queue(Constants.MqConstants.delayQueue, true, false, false, map));
        //队列和交换机绑定
        amqpAdmin.declareBinding(new Binding(Constants.MqConstants.delayQueue, Binding.DestinationType.QUEUE, Constants.MqConstants.delayExChange, Constants.MqConstants.delayKey, null));

        //创建一个普通队列
        amqpAdmin.declareQueue(new Queue(Constants.MqConstants.commonQueue, true));
        amqpAdmin.declareBinding(new Binding(Constants.MqConstants.commonQueue, Binding.DestinationType.QUEUE, Constants.MqConstants.delayExChange, Constants.MqConstants.commonKey, null));

    }

//    @RabbitListener(queues = Constants.MqConstants.delayQueue)
//    public void DelayConsumer(String message){
//        System.out.println(message);
//    }

    @RabbitListener(queues = Constants.MqConstants.commonQueue)
    public void CommonConsumer(String message){
        System.out.println("===========延时消息: " + message);
    }
}
