package com.spring_review.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Authority {
	private int authorityId;
	private int userId;
	private int roleId;
	private Role role; //role 조인이 될 거라서.
}
