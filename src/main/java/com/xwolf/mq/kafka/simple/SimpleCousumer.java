package com.xwolf.mq.kafka.simple;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author xwolf
 */
public class SimpleCousumer {

  private static final String TOPIC_NAME = "test";
  private static KafkaConsumer<String,String> consumer;
  private static final int OFFSET = 0;

  static {
    Properties properties = new Properties();
    //指定broker
    properties.setProperty("bootstrap.servers","localhost:9092,localhost:9093");
    properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
    properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
    consumer = new KafkaConsumer(properties);
  }

  public  static  void consumer(){
    //指定分区(从0开始),key,value
    ConsumerRecord<String,String> record =
        new ConsumerRecord(TOPIC_NAME,0,0,null,null);

       consumer.subscribe(Pattern.compile("test"));
  }

  public static void main(String[] args) {
    consumer();
  }
}
