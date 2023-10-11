package com.spring_review.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Configuration : ioc Container에 set
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//cross origin
		WebMvcConfigurer.super.addCorsMappings(registry);
		registry.addMapping("/**")      //모든 요청 엔드포인트
				.allowedMethods("*")    //모든 요청 메소드
				.allowedOrigins("*");   //모든 요청 서버  https://naver.com localhost:3000
//				.allowedOrigins("http://localhost:3000");
	}
}
