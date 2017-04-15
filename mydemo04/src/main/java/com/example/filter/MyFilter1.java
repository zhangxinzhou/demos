package com.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.Ordered;

@WebFilter
public class MyFilter1 implements Filter,Ordered{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器1初始化");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("执行过滤操作");
        chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		System.out.println("过滤器1销毁");
		
	}

	@Override
	public int getOrder() {//优先级,数字 越小优先级越高
		return 1;
	}

}
