package com.spring_review.todo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetTodoListRespDto {

	private int todoId;
	private String content;
	private String email;
}
