package com.xwolf.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author xwolf
 */
@Slf4j
public class UrlInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String uri = request.getRequestURI();
    log.info("interceptor uri = {}",uri);
    if (uri.contains("a")){
      Enumeration<String> params = request.getParameterNames();
      StringBuilder requestParam = new StringBuilder("?");
      while (params.hasMoreElements()){
        String name = params.nextElement();
        String value = request.getParameter(name);
        requestParam.append(name).append("=").append(value).append("&");
      }
      requestParam.append("x=aiyawai&y=wocaoa");
      response.sendRedirect("/test/b" + requestParam.toString());
      return false;
    }
    return super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }
}
