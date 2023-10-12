package com.spring_review.todo.service;

import com.spring_review.todo.dto.SigninReqDto;
import com.spring_review.todo.repository.UserMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

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

		String secret = "ePC9ZkhpkDMfz+AVY2Qd/29BfQahS2ISPSwu3gpLMfE=";
		Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

//	ex.	Date date = new Date("2023-10-13 12:34:00");
		Date date = new Date(new Date().getTime() + (1000 * 60 * 60 * 24)); // 1000ms(1초) * 60 * 60
		String jwtToken = Jwts.builder()
				.claim("username", authentication.getName())
				.claim("auth", authentication.getAuthorities())
				.setExpiration(date)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();

		System.out.println("jwtToken " + jwtToken);

		return  null;
	}
}
