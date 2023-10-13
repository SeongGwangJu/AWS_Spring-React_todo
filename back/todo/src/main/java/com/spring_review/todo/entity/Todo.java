package com.spring_review.todo.entity;

import com.spring_review.todo.dto.GetTodoListRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Todo {
	private int todoId;
	private String content;
	private String email;

	//Entity를 Dto로 바꾼다
	public GetTodoListRespDto toTodoListRespDto() {
		return GetTodoListRespDto.builder()
				.todoId(todoId)
				.content(content)
				.email(email)
				.build();
	}
}
