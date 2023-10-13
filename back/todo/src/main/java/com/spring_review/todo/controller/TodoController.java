package com.spring_review.todo.controller;

import com.spring_review.todo.dto.AddtodoReqDto;
import com.spring_review.todo.dto.UpdateTodoReqDto;
import com.spring_review.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

	@DeleteMapping("/todo/{todoId}")
	public ResponseEntity<?> deleteTodo(@PathVariable int todoId) { //경로의 변수 = todoId
		return ResponseEntity.ok().body(todoService.deleteTodo(todoId));
	}

	@PutMapping("/todo/{todoId}")
	public ResponseEntity<?> updateTodo(@PathVariable int todoId, @RequestBody UpdateTodoReqDto updateTodoReqDto) {
		return ResponseEntity.ok(todoService.updateTodo(todoId, updateTodoReqDto));

	}
}
