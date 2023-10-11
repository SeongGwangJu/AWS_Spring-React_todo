package com.spring_review.todo.service;

import com.spring_review.todo.dto.SignupReqDto;
import com.spring_review.todo.entity.User;
import com.spring_review.todo.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

	//DI
	private final UserMapper userMapper;
	private final BCryptPasswordEncoder passwordEncoder;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

//	private final JwtTokenProvider jwtTokenProvider;

//	Transactional : 이걸 실행하다가 Exceptiion이 생기면 rollback해라.
	@Transactional(rollbackFor = Exception.class)
	public Boolean signupUser(SignupReqDto signupReqDto) throws Exception {
		boolean result = false;
		User user = signupReqDto.toUserEntity(passwordEncoder);

//		userMapper.saveUser(user);
		result = userMapper.saveUser(user) > 0;

//        Integer executeCount = userMapper.saveUser(user);
//        System.out.println(executeCount);
		return result;
	}
}

