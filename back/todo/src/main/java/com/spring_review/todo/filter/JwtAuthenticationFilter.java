package com.spring_review.todo.filter;

import com.spring_review.todo.entity.Authority;
import com.spring_review.todo.entity.PrincipalUser;
import com.spring_review.todo.entity.Role;
import com.spring_review.todo.entity.User;
import com.spring_review.todo.repository.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Key;
import java.security.Security;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {


	private final UserMapper userMapper;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String authorization = httpServletRequest.getHeader("Authorization");
		// Bearer 토큰
		System.out.println("=====(2 : JwtFilter 시작 )");
		System.out.println("(2) authorization 값 : "+ authorization + " auth 끝.");

		String reqUri = httpServletRequest.getRequestURI();

		// "/auth"로 시작하면서 "/authenticated"로 시작하지 않을때
		if(reqUri.startsWith("/auth") && !reqUri.startsWith("/authenticated")) {
			chain.doFilter(request, response); //전처리 필터
			return;
		}

		//토큰이 없는 상황 =>
		if (!StringUtils.hasText(authorization)) {
			chain.doFilter(request, response);
			return;
		}

		//받아온 토큰을 파싱함.
		System.out.println("==== \"authenticated\"일 때의 JwtFilter ====");

		String accessToken = authorization.substring("Bearer ".length());
		System.out.println("(2) accessToken : " +accessToken);

		String secret = "ePC9ZkhpkDMfz+AVY2Qd/29BfQahS2ISPSwu3gpLMfE=";
		Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

//		Body를 얻음.
		Claims claims = null;
		try {
			claims = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(accessToken)
					.getBody();
		} catch (Exception e) { //토큰이 유효하지 않은 경우
			chain.doFilter(request, response);
			return;
		}

		String username = claims.get("username", String.class);
		System.out.println("(2) username(String.class의 값 : " + username);
		System.out.println("(2 claims.get(\"auth\", Collection.class) 값 : " + claims.get("auth", Collection.class));
		System.out.println("(2) claims.get(\"auth\", List.class) 값 : " + claims.get("auth", List.class));

// #DB에 들리는 방법
/*		User user = userMapper.findUserByEmail(username);
		System.out.println("In JwtFilter, user :" + user);

		PrincipalUser principalUser = new PrincipalUser(user);
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
		System.out.println("In JwtFilter, authentication : " + authentication );
		chain.doFilter(request, response);

		SecurityContextHolder.getContext().setAuthentication(authentication);*/

//Db에 들리지 않는 방법 -> 트래픽 줄어듬.
		List<Object> authList = claims.get("auth", List.class);
		//출력하면 [{authority=ROLE_USER}, {authority=ROLE_MANAGER}, {authority=ROLE_ADMIN}]
		List<Authority> authorities = new ArrayList<>();

		authList.forEach(auth -> {
			Role role = new Role();
			role.setRoleName(((Map<String, String>) auth).get("authority"));
			Authority authority = new Authority();
			authority.setRole(role);
			authorities.add(authority);
		});

		System.out.println("(2) authorities(List) : " + authorities);

		User beforeUser = new User();
		System.out.println("(2) user(builder Before) : " + beforeUser);

		User user = User.builder()
				.email(username)
				.authorities(authorities)
				.build();

		System.out.println("(2) user(builder After) : " + user);

		System.out.println("(2 : JwtFilter 끝 ======= )");

//		claims.get("auth");
	}
}
