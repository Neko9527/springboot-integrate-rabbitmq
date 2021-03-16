package com.neko9527.controller;

import com.neko9527.common.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
