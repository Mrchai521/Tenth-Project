package com.tensquare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: 柴新峰
 * @date: 2020-09-13 12:34
 * @description:
 */
public class RabbitApplication {
    //使用spring的测试代替junit的测试
    @RunWith(SpringRunner.class)
//指定测试程序的入口,springboot没有配置文件,只能指定启动类
    @SpringBootTest(classes = RabbitApplication.class)
    public class ProductTest {

        @Autowired
        private RabbitTemplate rabbitTemplate;


        @Test
        public void sendMsg(){
            rabbitTemplate.convertAndSend("firstQueue", "直接模式测试");
        }
    }
}
