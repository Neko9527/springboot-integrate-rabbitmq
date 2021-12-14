package com.neko9527.producer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxuechao
 * @date 2021-12-14
 */
@Configuration
public class TopicProducer {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange",true,false);
    }

    /**
     * 短信队列
     * @return
     */
    @Bean
    public Queue smsTopicQueue() {
        return new Queue("sms-queue",true);
    }

    /**
     * 邮件队列
     * @return
     */
    @Bean
    public Queue emailTopicQueue() {
        return new Queue("email-queue",true);
    }


    /**
     * 短信队列绑定交换机
     * @return
     */
    @Bean
    public Binding smsTopicBinding() {
        return BindingBuilder.bind(smsTopicQueue()).to(topicExchange()).with("*.sms.#");
    }

    /**
     * 邮件队列绑定交换机
     * @return
     */
    @Bean
    public Binding emailTopicBinding() {
        return BindingBuilder.bind(emailTopicQueue()).to(topicExchange()).with("#.email.*");
    }
}