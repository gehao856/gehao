package com.beixin.interceptor;

import com.beixin.util.JwtUtils2;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("执行后，返回前....");
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("处理过程中拦截。。。");
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //拦截器进行判断请求

        if("OPTIONS".equals(request.getMethod().toUpperCase())){
            return true;
        }
        //获取图片路径 放行
       // System.out.println("路径："+request.getRequestURI());;
        if(request.getRequestURI().contains("/images/")){
            return true;
        }

        //获取token
       String token= request.getHeader("token");
       if(token==null){
           return false;
       }else{
//           验证token
          Claims claims= JwtUtils2.checkJWT(token);
          if(claims!=null){
              return true;
          }
       }
        return true;
    }
}
