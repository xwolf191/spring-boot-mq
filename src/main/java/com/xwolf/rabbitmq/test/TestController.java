package com.xwolf.rabbitmq.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xwolf
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("get")
    public String get(@RequestParam("id")String id, @RequestBody String xml){
        log.info("get,id={},xml={}",id,xml);
        return xml;
    }

    @PostMapping("post")
    public String post(@RequestParam("id")String id, @RequestBody String xml){
        log.info("post ,id={},xml={}",id,xml);
        return xml;
    }
}
