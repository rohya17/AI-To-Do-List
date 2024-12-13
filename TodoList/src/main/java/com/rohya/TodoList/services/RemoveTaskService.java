package com.rohya.TodoList.services;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rohya.TodoList.repository.TodoListRepository;
import com.rohya.TodoList.utils.Utils;

public class RemoveTaskService implements Function<RemoveTaskService.Request, RemoveTaskService.Response> {

	private static final Logger logger = LoggerFactory.getLogger(RemoveTaskService.class);
	@Autowired TodoListRepository todoListRepository;
	public record Request(Integer id) {}
	public record Response(String response) {}
	
	@Override
	public Response apply(Request request) {
		
		logger.info("Remove task function called");
		
		if( Utils.isValidInteger(request.id()) ) {
			if(todoListRepository.existsById(request.id())) {
				todoListRepository.deleteById(request.id());
				logger.info("Removed task with id : "+request.id());
				return new Response("Task removed successfully.");
			}
			logger.info("Task not found with id : "+request.id());
			return new Response("Task not found.");
		}
		logger.error("Failed to remove task from to do list.");
		return new Response("Failed to remove task from to do list.");
	}
}
