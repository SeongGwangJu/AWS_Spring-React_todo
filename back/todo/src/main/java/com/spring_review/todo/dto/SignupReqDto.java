package com.spring_review.todo.dto;

import com.spring_review.todo.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignupReqDto {
	@Pattern(regexp = "^[가-힣]{2,6}$", message = "이름은 한글만 입력할 수 있습니다.")
	@NotBlank(message = "이름은 공백일 수 없습니다.")
	private String name;

	@Pattern(regexp = "^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-z]*$", message = "이메일을 입력하세요.")
	@NotBlank(message = "이메일은 공백일 수 없습니다.")
	private String email;

	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$", message = "비밀번호는 영문, 숫자 조합으로 8자 이상 입력하세요.")
	@NotBlank(message = "비밀번호는 공백일 수 없습니다.")
	private String password;

	public User toUserEntity(BCryptPasswordEncoder passwordEncoder) {
		return User.builder()
				.name(name)
				.email(email)
				.password(passwordEncoder.encode(password))
				.build();
	}
}
