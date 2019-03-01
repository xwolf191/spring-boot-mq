package com.xwolf.rabbitmq.core;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 简单的消费者
 * @author xwolf
 */
public class HelloConsumer {

  /**
   * 队列名称
   */
  private static final String QUEUE_NAME = "queue_hello";
  /**
   * IP
   */
  private static final String HOST = "localhost";
  /**
   * rabbitmq 用户名
   */
  private static final String USERNAME = "guest";

  /**
   * rabbitmq 密码
   */
  private static final String PASSWORD = "guest";

  /**
   * Rabbitmq端口
   */
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
           //创建通道
           Channel channel = connection.createChannel()
      ){
        /**
         * 最大获取的消息数量,0表示没有限制
         */
          channel.basicQos(1);
           //创建消费者
           Consumer consumer = new DefaultConsumer(channel){
                   @Override
                   public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                   String message = new String(body);
                   String formatMessage = String.format("收到的消息=%s,consumerTag=%s,envelope=%s,properties=%s"
                       ,message,consumerTag, JSONObject.toJSONString(envelope),JSONObject.toJSONString(properties));
                   System.out.println("receive message : "+ formatMessage);
                   /**
                    * 消费者确认消费消息,确认成功后会自动删除该消息
                    */
                   channel.basicAck(envelope.getDeliveryTag(),false);
               }
               };
          // 消费队列
          channel.basicConsume(QUEUE_NAME,consumer);
          //休眠5s,等待回调任务执行完成
          TimeUnit.SECONDS.sleep(5);
      }catch (Exception e){
         e.printStackTrace();
      }
}
}
