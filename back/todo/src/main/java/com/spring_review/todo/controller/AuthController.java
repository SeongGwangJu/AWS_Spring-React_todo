package com.spring_review.todo.controller;

import com.spring_review.todo.dto.SignupReqDto;
import com.spring_review.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	@PostMapping("/auth/signup")

	public ResponseEntity<?> signup(@RequestBody SignupReqDto signupReqDto,
	                                //결과는 bindingResult에 담긴다.
	                                BindingResult bindingResult) {

		//결과에 에러가 있다면
		if(bindingResult.hasErrors()) {
			//FieldError객체가 담긴 List를 돌면서
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError fieldError : bindingResult.getFieldErrors()) {
				// *get~이면 returnType을 꼭 볼것
				String fieldName = fieldError.getField();
				String message = fieldError.getDefaultMessage();
				errorMap.put(fieldName,message);

//				System.out.println(fieldName);
//				System.out.println(message);
			}

			return ResponseEntity.badRequest().body(errorMap);

		}
		userService.signupUser(signupReqDto);
		return ResponseEntity.ok().body("NonError");


	}
}
