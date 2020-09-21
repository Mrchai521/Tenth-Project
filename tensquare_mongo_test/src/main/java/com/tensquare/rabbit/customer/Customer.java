package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: 柴新峰
 * @date: 2020-09-13 12:38
 * @description:
 */
@Component
@RabbitListener(queues = "firstQueue")
public class Customer {
    @RabbitHandler
    public void getMsg(String msg) {
        System.out.println("直接模式消费消息: " + msg);
    }
}
