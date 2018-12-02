package com.xwolf.rabbitmq.core;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 简单的消费者
 * @author xwolf
 */
public class HelloConsumer {

    private static final String QUEUE_NAME = "queue_hello";
    private static final String HOST = "localhost";
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    //RabbitMQ 服务端默认端口号为 5672
    private static final int PORT = 5672;

    public static void main(String[] args) {
        Address[] addresses = {
                new Address(HOST,PORT)
        };
        ConnectionFactory factory = new ConnectionFactory() ;
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        try( //创建连接
             Connection connection = factory.newConnection(addresses);
             //创建信道
             Channel channel = connection.createChannel()
        ){
            //最大获取的消息数量
            channel.basicQos(50);
             //创建消费者
             Consumer consumer = new DefaultConsumer(channel){
                     @Override
                     public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                     String message = new String(body);
                     System.out.println("receive message : "+ message);
                     channel.basicAck(envelope.getDeliveryTag(),true);
                 }
                 };
            channel.basicConsume(QUEUE_NAME,consumer);
            //休眠5s,等待回调任务执行完成
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
           e.printStackTrace();
        }
}
}
