package com.xwolf.mq.rabbitmq.core;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;
import java.util.stream.IntStream;

/**
 * 简单的生产者
 * @author xwolf
 */
public class HelloProduct {

    private static final String EXCHANGE_NAME = "exchange_hello";
    private static final String ROUTING_KEY = " routingkey_hello";
    private static final String QUEUE_NAME = "queue_hello";
    private static final String HOST = "localhost";
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    //RabbitMQ 服务端默认端口号为 5672
    private static final int PORT = 5672;

    private static ConnectionFactory factory;

    static{
        factory = new ConnectionFactory() ;
        factory.setHost(HOST);
        factory.setPort(PORT) ;
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
    }

    public static void main(String[] args){

         try( //创建连接
             Connection connection = factory.newConnection();
             //创建信道
             Channel channel = connection.createChannel()){
             // 创建一个 type="direct" 、持久化的、非自动删除的交换器
             channel.exchangeDeclare(EXCHANGE_NAME, "direct" , true , false , null) ;
             //创建一个持久化、非排他的、非自动删除的队列
             channel. queueDeclare(QUEUE_NAME, true, false , false , null) ;
             //将交换器与队列通过路由键绑定
             channel.queueBind(QUEUE_NAME , EXCHANGE_NAME , ROUTING_KEY);
             //发送一条持久化的消息: hello world !
             IntStream.range(0,1000).forEach(x -> {
                 String message = String.format("生产者发送消息=%s,当前发送次数=%d,发送时间=%d","Hello RabbitMQ.",x,System.currentTimeMillis());
                 try {
                     channel.basicPublish( EXCHANGE_NAME,ROUTING_KEY ,
                         MessageProperties.PERSISTENT_TEXT_PLAIN,
                         message.getBytes()) ;
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             });

         }catch (Exception e){
             e.printStackTrace();
         }
   }

}
