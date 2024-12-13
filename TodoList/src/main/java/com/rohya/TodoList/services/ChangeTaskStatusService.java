package com.rohya.TodoList.services;

import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.rohya.TodoList.model.TodoList;
import com.rohya.TodoList.repository.TodoListRepository;
import com.rohya.TodoList.utils.Utils;

public class ChangeTaskStatusService implements Function<ChangeTaskStatusService.Request, ChangeTaskStatusService.Response>{

	private static final Logger logger = LoggerFactory.getLogger(ChangeTaskStatusService.class);
	@Autowired private TodoListRepository todoListRepository;
	public record Request( Integer id, String status ) {}
	public record Response( String response ) {}
	
	@Override
	public Response apply(Request request) {
		
		logger.info("Change task status function called");
		
		if(request == null) {
			logger.error("ChangeTaskStatusService Invalid request");
			return new Response("Invalid Request.");
		}
		
		if( isValidRequest(request) ) {
			
			logger.info("Request parameters, status : "+request.status()+" - taskid : "+request.id() );
			if(!request.status().equalsIgnoreCase("pending") && !request.status().equalsIgnoreCase("completed")) {
				return new Response("Invalid status : "+request.status());			
			}
			
			Optional<TodoList> taskOptional = todoListRepository.findById(request.id());
			if(!taskOptional.isPresent()) {
				logger.info("Task not found for task id : "+request.id());
				return new Response("Task not found");
			}
			TodoList task = taskOptional.get();
			if( request.status().equalsIgnoreCase("completed") ) {
				task.setCompleted(Boolean.TRUE);
			}else if( request.status().equalsIgnoreCase("pending") ) {
				task.setCompleted(Boolean.FALSE);
			}
			todoListRepository.save(task);
			logger.info("Task "+task.getTask()+" status updated to : "+request.status());
			return new Response("Task status updated successfully");
		}
		
		return new Response("Insufficient/invalid request parameters");
	}

	private boolean isValidRequest(Request request) {
		return Utils.isValidInteger(request.id()) && Utils.isValidString(request.status());
	}
	
}
