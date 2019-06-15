package com.xwolf.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xwolf
 */
@Slf4j
public class UrlFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String uri = request.getRequestURI();
    log.info("uri= {}",uri);
    if (uri.contains("a")){
      String path = uri + "?abc=32&fb=12";
      request.getRequestDispatcher(path).forward(request,response);
    } else {
      filterChain.doFilter(request,response);
    }


  }
}
