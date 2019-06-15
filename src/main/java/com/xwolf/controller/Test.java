package com.xwolf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xwolf
 */
@RestController
@RequestMapping("test")
public class Test {

  @GetMapping("a")
  public String test(@RequestParam(value = "abc",required = false,defaultValue = "0")
  String abc,
      @RequestParam(value = "fb",required = false,defaultValue = "0") String fb,
      @RequestParam(value = "x",required = false,defaultValue = "0") String x,
      @RequestParam(value = "y",required = false,defaultValue = "0") String y){
    return abc + "," + fb + "," + x + "," + y;
  }
  @GetMapping("b")
  public String testB(@RequestParam(value = "abc",required = false,defaultValue = "0")
      String abc,
      @RequestParam(value = "fb",required = false,defaultValue = "0") String fb,
      @RequestParam(value = "x",required = false,defaultValue = "0") String x,
      @RequestParam(value = "y",required = false,defaultValue = "0") String y){
    return abc + "," + fb + "," + x + "," + y;
  }

}
