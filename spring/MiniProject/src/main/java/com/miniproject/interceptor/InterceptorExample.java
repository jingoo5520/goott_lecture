package com.miniproject.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterceptorExample extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandler() 동작");
		return true; // 해당 컨트롤러단의 메서드로 제어가 돌아감
		// return false; // 해당 컨트롤러단의 메서드로 제어가 돌아가지 않음 
		
//		HandlerMethod method = (HandlerMethod) handler;
//		 Method methodobj = method.getMethod();
//		
//		System.out.println("Bean: " + method.getBean());
//		System.out.println("Method: " + methodobj);
	}

	// 지정된 컨트롤러의 동작 이후에 처리되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Handler() 동작");
		super.postHandle(request, response, handler, modelAndView);
	}

	
	// view단 처리가 완료된 후 실행되는 메서드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion() 동작");
		super.afterCompletion(request, response, handler, ex);
	}

}
