package com.rohya.TodoList.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todolist")
public class TodoList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String task;
	private LocalDateTime deadline;
	private boolean completed;
	
	public TodoList() {
		super();
	}
	public TodoList(String task, String deadline) {
		super();
		this.task = task;
		this.deadline = LocalDateTime.parse(deadline);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	@Override
	public String toString() {
		return "TodoList [id=" + id + ", task=" + task + ", deadline=" + deadline + ", completed=" + completed + "]";
	}
}
