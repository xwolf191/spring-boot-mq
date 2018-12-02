package com.xwolf.rabbitmq.confirms;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * 生产者通过事务机制保证消息成功发送到MQ中
 * @author xwolf
 */
public class ProductTransaction {
    private static final String EXCHANGE_NAME = "exchange_hello";
    private static final String ROUTING_KEY = " routingkey_hello";
    private static final String QUEUE_NAME = "queue_hello";
    private static final String HOST = "localhost";
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    //RabbitMQ 服务端默认端口号为 5672
    private static final int PORT = 5672;

    public static void main(String[] args){
        ConnectionFactory factory = new ConnectionFactory() ;
        factory.setHost(HOST);
        factory.setPort(PORT) ;
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        Connection connection = null;
        Channel channel = null;
        try{
            //创建连接
             connection = factory.newConnection();
            //创建信道
             channel = connection.createChannel();
            //开启事务
            channel.txSelect();
            // 创建一个 type="direct" 、持久化的、非自动删除的交换器
            channel.exchangeDeclare(EXCHANGE_NAME, "direct" , true , false , null) ;
            //创建一个持久化、非排他的、非自动删除的队列
            channel. queueDeclare(QUEUE_NAME, true, false , false , null) ;
            //将交换器与队列通过路由键绑定
            channel.queueBind(QUEUE_NAME , EXCHANGE_NAME , ROUTING_KEY);
            //发送一条持久化的消息: hello world !
            String message = "Hello World,生产者发送事务机制."+ new Date();
            channel.basicPublish( EXCHANGE_NAME,ROUTING_KEY ,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes()) ;
            //提交
            channel.txCommit();
        }catch (Exception e){
            e.printStackTrace();
            try {
                //回滚
                channel.txRollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally {
            if (channel.isOpen()){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection.isOpen()){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
