package com.neko9527.producer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxuechao
 * @date 2021-12-14
 */
@Configuration
public class HeadersProducer {

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headers-exchange",true,false);
    }

    /**
     * 短信队列
     * @return
     */
    @Bean
    public Queue smsHeaderQueue() {
        return new Queue("sms-queue",true);
    }

    /**
     * 短信队列
     * @return
     */
    @Bean
    public Queue emailHeaderQueue() {
        return new Queue("email-queue",true);
    }


    @Bean
    public Binding smsHeadersBinding() {
        //x = 1
        return BindingBuilder.bind(smsHeaderQueue()).to(headersExchange()).where("x").matches(1);
    }

    @Bean
    public Binding emailHeadersBinding() {
        //x,y any exist
        return BindingBuilder.bind(emailHeaderQueue()).to(headersExchange())
                .whereAny("x","y").exist();
    }
}
