package com.spring_review.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	private Integer userId;
	private String email;
	private String name;
	private String password;
	private String provider;
}
