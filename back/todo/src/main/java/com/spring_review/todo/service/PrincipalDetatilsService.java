package com.spring_review.todo.service;

import com.spring_review.todo.entity.PrincipalUser;
import com.spring_review.todo.entity.User;
import com.spring_review.todo.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetatilsService implements UserDetailsService {

	private final UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername : " + email);

		User user = userMapper.findUserByEmail(email);

		if(user == null) {
			return null;
		}
		return new PrincipalUser(user);
	}
}
