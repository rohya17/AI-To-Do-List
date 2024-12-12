package com.rohya.TodoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohya.TodoList.model.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {

}
