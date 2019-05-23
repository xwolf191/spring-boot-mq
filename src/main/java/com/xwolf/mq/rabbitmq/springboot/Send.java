package com.xwolf.mq.rabbitmq.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wolf
 */
@Slf4j
@Component
public class Send  {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(String routekey,String message){
      log.info("Send Message: routekey={},message={}",routekey,message);
      rabbitTemplate.convertAndSend(routekey,message+" "+ new Date());
    }

    /*@Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("CorrelationData={},ack={},cause={}",correlationData,ack,cause);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("message={},replyCode={},replyText={}",message,replyCode,replyText);

    }*/
}
