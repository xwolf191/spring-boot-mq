package com.xwolf.rabbitmq.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wolf
 */
@Slf4j
@Component
@RabbitListener(queues = {"hello"})
public class Receiver {

    @RabbitHandler
    public void process(String message) {
        log.info("Receiver  : {}" , message);
        System.out.println("Receiver  : " + message);
    }

}
