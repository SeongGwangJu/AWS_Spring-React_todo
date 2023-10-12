package com.spring_review.todo.service;

import com.spring_review.todo.dto.SigninReqDto;
import com.spring_review.todo.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserMapper userMapper;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	public Boolean isDuplicatedEmail(String email) {
		boolean result = false;

//방법(1) null이 아니면 true(중복O), null이면 false(중복x)
//		result = userMapper.findUserByEmail(email) != null;

//방법(2) userCount가 0보다 크면 중복임.
		result = userMapper.getUserCountByEmail(email) > 0;
		System.out.println("duplicate Check Result : " + result + " *true = 중복. false= 중복 x => 가입 성공");
		return result;
	}

	public String signin(SigninReqDto signinReqDto) {
		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(signinReqDto.getEmail(), signinReqDto.getPassword());

		System.out.println("AuthService-Signin -- (1)");
		System.out.println("token : " + token);
		//return type : authentication
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
		System.out.println("authentication 객체 : " + authentication);
		System.out.println("authentication.getName() : " +authentication.getName());
		return  null;
	}
}
