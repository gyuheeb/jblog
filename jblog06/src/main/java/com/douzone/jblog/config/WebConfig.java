package com.douzone.jblog.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.douzone.jblog.security.LoginInterceptor;
import com.douzone.jblog.security.LogoutInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer{


	
	//Security Interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(loginInterceptor())
			.addPathPatterns("/user/login");
		
		registry
			.addInterceptor(logoutInterceptor())
			.addPathPatterns("/index");

	}
	
	
	
}
