package com.spring_review.todo.controller;

import com.spring_review.todo.dto.SigninReqDto;
import com.spring_review.todo.dto.SignupReqDto;
import com.spring_review.todo.service.AuthService;
import com.spring_review.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

//	@Autowired
	private final UserService userService;
//	@Autowired
	private final AuthService authService;

	@PostMapping("/auth/signup")
	// @Valid-> 유효성 검사를 함. 결과는 bindingResult에 담긴다. 이 2개는 세트.
	public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult) throws Exception {

		//결과에 에러가 있다면->True
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			//FieldError객체가 담긴 List를 돌면서 errorMap에 넣는다.
			for(FieldError fieldError : bindingResult.getFieldErrors()) {
				// *get~이면 return Type을 꼭 확인할 것!
				String fieldName = fieldError.getField();
				String message = fieldError.getDefaultMessage();
				errorMap.put(fieldName, message);

				System.out.println(fieldName); //email or name ..
				System.out.println(message); //"이메일 형식을 지켜주세요" or "공백일 수 없습니다 등.
			}
			return ResponseEntity.badRequest().body(errorMap);
		}

		//중복 검사
		if (authService.isDuplicatedEmail(signupReqDto.getEmail())) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("email", "이미 사용중인 이메일입니다.");
			return ResponseEntity.badRequest().body(errorMap);
		}

		userService.signupUser(signupReqDto);
		return ResponseEntity.ok().body(true);
	}

	@PostMapping("/auth/signin")
	public ResponseEntity<?> signin(@RequestBody SigninReqDto signinReqDto) {
		System.out.println("========= AuthController - /auth/signin =========");
		return ResponseEntity.ok().body(authService.signin(signinReqDto));
	}

	@GetMapping("/authenticated")
	public ResponseEntity<?> authenticated() {
		System.out.println("========= AuthController - /authenticated =========");
		return ResponseEntity.ok(true);
	}
}
