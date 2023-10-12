package com.spring_review.todo.config;


import com.spring_review.todo.exception.CustomAuthenticationEntryPoint;
import com.spring_review.todo.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity //Spring Security활성화
@Configuration //Bean 구성 클래스
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	//암호화 객체 생성 + IoC에 passwordEncoder라는 이름으로, Bean 등록.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//http 보안구성 정의.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("SecurityConfig 실행");
		http.cors(); //cors 활성화
		http.csrf().disable();  //CSRF(Cross-Site Request Forgery) 보호를 비활성화
		http.authorizeRequests() //접근권한 설정
				.antMatchers("/auth/**") //모든 사용자(로그인 없이도) "/auth/" url접근 허용.
				.permitAll()
				.antMatchers("admin/**")
				.hasRole("admin") //admin 권한이 있는지 확인.
				.anyRequest()
				.authenticated()
				.and()
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //UPAFilter거치기 전에 jwt필터먼저 거침.
				.exceptionHandling() //AuthService 에서 authentication중 흐름이 끊길때의 예외 처리
				.authenticationEntryPoint(customAuthenticationEntryPoint); //매개변수를 보면 authenticationEntryPoint를 넣어줘야함.
	}
}
