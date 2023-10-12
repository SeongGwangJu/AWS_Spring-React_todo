package com.spring_review.todo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PrincipalUser implements UserDetails {
//	private String username;
//	private String password;

	private User user;

	public PrincipalUser(User user) {
		System.out.println("findUserByEmail 결과 user가 조회되었으므로 PrincipalUser 생성. (3)");
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.print("0 Collection");
		return null;
	}

	@Override
	public String getPassword() {
		System.out.print("1 getPassword");
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		System.out.print("2 getUsername");
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		System.out.print("3 isAccountNonExpired");
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		System.out.print("4 isAccountNonLocked");
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		System.out.print("5 isCredentialsNonExpired");
		return true;
	}

	@Override
	public boolean isEnabled() {
		System.out.print("6 isEnabled");
		return true;
	}
}
