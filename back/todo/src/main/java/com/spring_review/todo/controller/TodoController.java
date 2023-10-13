package com.spring_review.todo.controller;

import com.spring_review.todo.dto.AddtodoReqDto;
import com.spring_review.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	@PostMapping("/todo")
	public ResponseEntity<?> addTodo(@RequestBody AddtodoReqDto addtodoReqDto) {
		System.out.println("TodoController에서 Dto :" + addtodoReqDto);

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("TodoController에서 username : " + username);
		return ResponseEntity.ok(todoService.addTodo(addtodoReqDto));
	}

	@GetMapping("/todo/list")
	public ResponseEntity<?> getTodoList() {

		return ResponseEntity.ok().body(todoService.getTodoList());

	}
}
