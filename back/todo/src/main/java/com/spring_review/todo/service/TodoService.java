package com.spring_review.todo.service;

import com.spring_review.todo.dto.AddtodoReqDto;
import com.spring_review.todo.dto.GetTodoListRespDto;
import com.spring_review.todo.entity.Todo;
import com.spring_review.todo.repository.TodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

	private final TodoMapper todoMapper;

	@Transactional(rollbackFor = Exception.class)
	public Boolean addTodo(AddtodoReqDto addtodoReqDto) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(email);

		Todo todo = Todo.builder()
				.content(addtodoReqDto.getContent())
				.email(email)
				.build();
		System.out.println("입력");
		return todoMapper.saveTodo(todo) > 0;
	}

	public List<GetTodoListRespDto> getTodoList() {
		// JwtAuthenticationFilter에서 인증받으려고 넣어둔 Authentication을 다시 꺼내서 email가져옴.
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(email);
		List<GetTodoListRespDto> getTodoListRespDtos = new ArrayList<>();

		todoMapper.getTodoListByEmail(email).forEach(todo -> {
			getTodoListRespDtos.add(todo.toTodoListRespDto());
		});

		return getTodoListRespDtos;
	}
}
