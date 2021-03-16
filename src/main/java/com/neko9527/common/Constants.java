package com.neko9527.common;

/**
 * @author wangxuechao
 * @date 2021-03-16
 */
public class Constants {

    public interface MqConstants {
        String delayExChange = "delay_exchange";
        //延时队列
        String delayQueue = "delay_queue";
        //普通队列
        String commonQueue = "my_queue";
        //延时队列binding key
        String delayKey = "delay_key";
        //普通队列binding key
        String commonKey = "my_key";
    }
}
