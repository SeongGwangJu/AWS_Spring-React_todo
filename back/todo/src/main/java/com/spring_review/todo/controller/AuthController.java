package com.spring_review.todo.controller;

import com.spring_review.todo.dto.SignupReqDto;
import com.spring_review.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

	//me
	private final UserService userService;

	@PostMapping("/auth/signup")
	public ResponseEntity<?> signup(@RequestBody SignupReqDto signupReqDto) {
		//me
		userService.signupUser(signupReqDto);
		return ResponseEntity.ok().body(null);


	}
}
