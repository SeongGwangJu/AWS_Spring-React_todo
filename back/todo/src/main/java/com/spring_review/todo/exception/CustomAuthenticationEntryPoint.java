package com.spring_review.todo.exception;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

		System.out.println("시큐리티의 모든 예외는 여기(CAEP)에서 처리함.");
		System.out.println("EntryPoint의 commence 안의 예외.class : " +authException.getClass());

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", getErrorMessage(authException));

		JsonMapper jsonMapper = new JsonMapper();
		// jackson라이브러리 Map을 JSON문자열로 변환
		String responseJson = jsonMapper.writeValueAsString(errorMap);

		response.getWriter().println(responseJson);
	}

	private String getErrorMessage(AuthenticationException authException) {
		if(authException.getClass() == BadCredentialsException.class) {
			return "BadCredentialsException 잘못된 사용자 정보입니다. 다시 확인하세요.";
		}else if(authException.getClass() == UsernameNotFoundException.class) {
			return "UsernameNotFoundException 잘못된 사용자 정보입니다. 다시 확인하세요.";
		}else if(authException.getClass() == AccountExpiredException.class) {
			return "AccountExpiredException 만료된 사용자 정보입니다. 다시 확인하세요.";
		}else if(authException.getClass() == CredentialsExpiredException.class) {
			return "CredentialsExpiredException 인증서가 만료되었습니다. 관리자에게 문의하세요.";
		}else if(authException.getClass() == DisabledException.class) {
			return "DisabledException 비활성화된 사용자 정보입니다. 관리자에게 문의하세요.";
		}else if(authException.getClass() == LockedException.class) {
			return "LockedException 암호 오류 5회 이상. 관리자에게 문의하세요.";
		}else {
			return "사용자 정보 오류.";
		}
	}


}

