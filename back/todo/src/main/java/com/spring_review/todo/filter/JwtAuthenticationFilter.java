package com.spring_review.todo.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Key;
import java.util.Collection;

@Component
public class JwtAuthenticationFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String authorization = httpServletRequest.getHeader("Authorization");
		// Bearer 토큰
		System.out.println(authorization);

		if (!StringUtils.hasText(authorization)) {
			throw new AuthenticationException();
		}

		//받아온 토큰을 파싱함.
		String accessToken = authorization.substring("Bearer ".length());
		String secret = "ePC9ZkhpkDMfz+AVY2Qd/29BfQahS2ISPSwu3gpLMfE=";
		Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

//		Body를 얻음.
		Claims claims;
		try {
			claims = Jwts.parserBuilder().setSigningKey(key).build()
					.parseClaimsJws(accessToken).getBody();
		} catch (Exception e) {
			throw new AuthenticationException();
		}

		String username = claims.get("username", String.class);
		System.out.println(claims.get("auth", Collection.class));

		claims.get("auth");


	}
}
