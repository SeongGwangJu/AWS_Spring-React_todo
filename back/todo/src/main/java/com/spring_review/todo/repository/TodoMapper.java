package com.spring_review.todo.repository;

import com.spring_review.todo.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
	public int saveTodo(Todo todo);
	public List<Todo> getTodoListByEmail(String email);

}
