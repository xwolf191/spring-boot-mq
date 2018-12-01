package com.xwolf.rabbitmq;

import com.xwolf.rabbitmq.springboot.Send;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqApplicationTests {
    @Autowired
    private Send send;

    @Test
    public void contextLoads() {
        System.out.println("contextLoads...");
    }

    @Test
    public void testMQ(){
        send.send("hello","rabbitmq");
    }

}
