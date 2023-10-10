package com.spring_review.todo.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //Spring Security활성화
@Configuration //Bean 구성 클래스
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//http 보안구성 정의.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors(); //cors 활성화
		http.csrf().disable();  //CSRF(Cross-Site Request Forgery) 보호를 비활성화
		http.authorizeRequests() //접근권한 설정
				.antMatchers("/auth/**") //모든 사용자(로그인 없이도) "/auth/" url접근 허용.
				.permitAll();
	}

	//암호화 객체 생성 + SpringContainer에 Bean으로 등록함.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
