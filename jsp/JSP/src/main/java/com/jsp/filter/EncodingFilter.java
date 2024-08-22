package com.jsp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/")
public class EncodingFilter implements Filter {

	String charset = "utf-8";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("필터 동작중...");
		
		request.setCharacterEncoding(charset);
		
		response.setContentType("text/html; charset=" + charset);
		chain.doFilter(request, response); // 서블릿 요청이 이어짐
		
		// 후처리
	}
}