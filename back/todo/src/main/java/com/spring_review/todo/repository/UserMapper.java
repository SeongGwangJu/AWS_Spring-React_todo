package com.spring_review.todo.repository;

import com.spring_review.todo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public Integer saveUser(User user);
}