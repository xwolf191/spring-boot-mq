package com.xwolf.mq.kafka.simple;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author xwolf
 */
public class SimpleCousumer {

  private static final String TOPIC_NAME = "test";
  private static KafkaConsumer<String,String> consumer;

  static {
    Properties properties = new Properties();
    //指定broker
    properties.setProperty("bootstrap.servers","localhost:9092,localhost:9093");
    //消费者群组ID
    properties.setProperty("group.id","gp");
    //反序列化
    properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
    properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
    consumer = new KafkaConsumer(properties);
  }

  public  static  void consumer(){
       consumer.subscribe(Collections.singletonList(TOPIC_NAME));
       while(true){
         ConsumerRecords<String,String> records = consumer.poll(1000);
         Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
         while (iterator.hasNext()){
           ConsumerRecord<String, String> consumerRecord = iterator.next();
           System.out.printf(
               "value=%s,toString=%s,topic=%s,key=%s,timestamp=%d",
               consumerRecord.value(),
               consumerRecord.toString(),
               consumerRecord.topic(),
               consumerRecord.key(),
               consumerRecord.timestamp()
           );
         }
        consumer.commitAsync();
       }
  }

  public static void main(String[] args) {
    consumer();
  }
}
