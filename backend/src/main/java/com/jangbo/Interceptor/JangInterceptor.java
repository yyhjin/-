package com.jangbo.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JangInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.print("시작합니다~~");
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
