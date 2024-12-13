package com.rohya.TodoList.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rohya.TodoList.model.TodoList;
import com.rohya.TodoList.repository.TodoListRepository;

public class ShowTaskService implements Function<ShowTaskService.Request, ShowTaskService.Response> {

	private static final Logger logger = LoggerFactory.getLogger(ShowTaskService.class);
	@Autowired private TodoListRepository todoListRepository;
	public record Request( String request ) {}
	public record Response( List<Map<String, String>> response ) {}
	
	@Override
	public Response apply(Request request) {
		
		logger.info("Show all tasks function called");
		
		if(request == null) {
			logger.error("ShowTaskService Invalid Request");
			return new Response( Arrays.asList(Map.of("message","Invalid Request")) );
		}
		
		List<Map<String, String>> responseList = new ArrayList<>();
		
		logger.info("ShowTaskService Request : "+request.request());
		List<TodoList> toDoList = todoListRepository.findAll();
		for (TodoList todo : toDoList) {
			Map<String,String> todomap = new HashMap<>();
			todomap.put("taskId", todo.getId()+"");
			todomap.put("task", todo.getTask());
			todomap.put("deadline", todo.getDeadline().toString());
			todomap.put("status", todo.isCompleted() ? "Completed" : "Pending");
			responseList.add(todomap);
		}
		
		return new Response(responseList);
	}
	
	
}
