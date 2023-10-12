package com.spring_review.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Builder //insert가 일어날때는 Dto to entity Convert가 필요 =>@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	private Integer userId;
	private String email;
	private String name;
	private String password;
	private String provider;
	private List<Authority> authorities;

	//SpringSecurity가 알수있는 Type인 SimpleGrantedAuthority.
	public List<SimpleGrantedAuthority> toGrantedAuthorityList() {
		List<SimpleGrantedAuthority> simpleGrantedAuthorities =
				new ArrayList<>();
		authorities.forEach(authority -> simpleGrantedAuthorities.add(
				new SimpleGrantedAuthority(
						authority.getRole()
								.getRoleName())));
		return simpleGrantedAuthorities;
	}

}
