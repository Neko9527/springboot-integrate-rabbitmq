package com.neko9527.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxuechao
 * @date 2021-12-10
 */
@Configuration
public class FanoutProducer {


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange",true,false);
    }

    /**
     * 短信队列
     * @return
     */
    @Bean
    public Queue smsQueue() {
        return new Queue("sms-queue",true);
    }

    /**
     * 邮件队列
     * @return
     */
    @Bean
    public Queue emailQueue() {
        return new Queue("email-queue",true);
    }


    /**
     * 短信队列绑定交换机
     * @return
     */
    @Bean
    public Binding smsBinding() {
        //fanout模式不需要routingKey
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    /**
     * 邮件队列绑定交换机
     * @return
     */
    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
}
