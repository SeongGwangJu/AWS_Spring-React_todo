package com.spring_review.todo.service;

import com.spring_review.todo.entity.PrincipalUser;
import com.spring_review.todo.entity.User;
import com.spring_review.todo.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
@RequiredArgsConstructor
public class PrincipalDetatilsService implements UserDetailsService {

	private final UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("===== (3) PrincipalDetailsService 시작");
		System.out.println("(3)입력한 email : " + email + " (*printed in loadUserByUsername()");

		User user = userMapper.findUserByEmail(email);

		if(user == null) {
			System.out.println("(3) 조회된 user가 없으므로 null을 리턴함."); //id가 틀렸을 경우임.
			return null;
		}
		System.out.println("(3) user가 null이 아님 => principalUser를 Return.");
		return new PrincipalUser(user);
	}
}
