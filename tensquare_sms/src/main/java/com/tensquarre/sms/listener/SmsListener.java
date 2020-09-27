package com.tensquarre.sms.listener;

import com.alibaba.fastjson.JSON;
import com.tensquarre.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 柴新峰
 * @date: 2020-09-21 23:32
 * @description: 消息的消费者
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    /**
     * 注入短信的模板
     */
    @Value("${aliyun.sms.templateCode}")
    private String templateCode;

    /**
     * 注入短信的签名
     */
    @Value("${aliyun.sms.signName}")
    private String signName;

    @Autowired
    private SmsUtil smsUtil;

    @RabbitHandler
    public void executeSms(Map map) {
        //用户手机号
        String mobile = (String) map.get("mobile");
        //随机生成的验证码
        String checkCode = (String) map.get("checkCode");

        System.out.println(mobile);
        System.out.println(checkCode);
        //定义发送短信消息所用的map集合
        HashMap<Object, Object> smsMap = new HashMap<>();
        //此处的键值code,要与短信模板中的占位符中的一致,否则会报错
        smsMap.put("code", checkCode);
        try {
            //执行发送消息
            smsUtil.sendSms(mobile, checkCode, signName, JSON.toJSONString(smsMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
