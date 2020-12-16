package com.example.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/13 - 0:04
 * @author: Mr_Bangb
 */
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @RequestMapping("/queue")
    public String queue(){

        for (int i = 0; i < 10 ; i++){
            jms.convertAndSend(queue, "queue"+i);
        }

        return "queue 发送成功";
    }

    @JmsListener(destination = "out.queue")
    public void consumerMsg(String msg){
        System.out.println(msg);
    }

    @RequestMapping("/topic")
    public String topic(){

        for (int i = 0; i < 10 ; i++){
            jms.convertAndSend(topic, "topic"+i);
        }

        return "topic 发送成功";
    }
}