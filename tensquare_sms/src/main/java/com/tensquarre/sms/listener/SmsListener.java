package com.tensquarre.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: 柴新峰
 * @date: 2020-09-21 23:32
 * @description: 消息的消费者
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @RabbitHandler
    public void executeSms(Map map){
        System.out.println(map.get("mobile"));
        System.out.println(map.get("checkCode"));
    }
}
