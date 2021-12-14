package com.neko9527.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxuechao
 * @date 2021-12-14
 */
@Configuration
public class DirectProducer {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange", true, false);
    }

    /**
     * 短信队列
     *
     * @return
     */
    @Bean
    public Queue smsDirectQueue() {
        return new Queue("sms-queue", true);
    }

    /**
     * 邮件队列
     *
     * @return
     */
    @Bean
    public Queue emailDirectQueue() {
        return new Queue("email-queue", true);
    }


    /**
     * 短信队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding smsDirectBinding() {
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms");
    }

    /**
     * 邮件队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding emailDirectBinding() {
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("email");
    }
}
