package com.spring_review.todo.repository;

import com.spring_review.todo.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {
	public int saveTodo(Todo todo);

}
