package com.xwolf.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xwolf
 */
@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean registrationBean(){
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new UrlFilter());
    registration.addUrlPatterns("/*");
    registration.setName("UrlFilter");
    registration.setOrder(1);
    return registration;
  }

}
