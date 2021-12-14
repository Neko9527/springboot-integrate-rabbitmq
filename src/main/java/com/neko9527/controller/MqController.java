package com.neko9527.controller;

import com.neko9527.common.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author wangxuechao
 * @date 2021-03-15
 */
@RestController
@RequestMapping("/mq")
public class MqController {

    @Resource
    private RabbitTemplate template;
    @Resource
    private AmqpAdmin amqpAdmin;

    /**
     * 延时队列
     * @param msg
     */
    @PostMapping("/send")
    public void send(String msg) {
        template.convertAndSend(Constants.MqConstants.delayExChange, Constants.MqConstants.delayKey, msg);
    }

    @DeleteMapping("/delete")
    public void delete() {
        //删除队列和交换机
        amqpAdmin.deleteQueue(Constants.MqConstants.delayQueue);
        amqpAdmin.deleteQueue(Constants.MqConstants.commonQueue);
        amqpAdmin.deleteExchange(Constants.MqConstants.delayExChange);
    }

    /**
     * fanout模式
     */
    @PostMapping("/fanout")
    public void saveOrder() {
        //创建订单
        String orderId = UUID.randomUUID().toString();
        //减少库存
        //....
        //发送消息
        template.convertAndSend("fanout-exchange","",orderId);
    }

    @PostMapping("/direct")
    public void direct() {
        String str = UUID.randomUUID().toString();
        template.convertAndSend("direct-exchange","sms",str);
    }

    @PostMapping("/topic")
    public void topic() {
        String str = UUID.randomUUID().toString();
        template.convertAndSend("topic-exchange","com.sms.email.test",str);
    }
}
