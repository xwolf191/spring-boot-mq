package com.xwolf.mq.kafka.simple;

import com.alibaba.fastjson.JSON;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author xwolf
 */
public class SimpleProducter {

  private static final String TOPIC_NAME = "test";
  private static KafkaProducer<String,String> producer;
  static {
    Properties properties = new Properties();
    //指定broker
    properties.setProperty("bootstrap.servers","localhost:9092,localhost:9093");
    properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    producer = new KafkaProducer<>(properties);
  }

  public  static  void send(){
    String key = "helloKey";
    String value = "Hello world.";
    //指定分区(从0开始),key,value
    ProducerRecord<String,String> record =
        new ProducerRecord(TOPIC_NAME,0,System.currentTimeMillis(),key,value);

    try {
      RecordMetadata recordMetadata = producer.send(record).get();
      System.out.println(recordMetadata.toString());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    send();
  }

}
