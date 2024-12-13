package com.rohya.TodoList.services;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rohya.TodoList.model.TodoList;
import com.rohya.TodoList.repository.TodoListRepository;
import com.rohya.TodoList.utils.Utils;

public class AddTaskService implements Function<AddTaskService.Request, AddTaskService.Response> {
	
	private static final Logger logger = LoggerFactory.getLogger(AddTaskService.class);
	@Autowired private TodoListRepository todoListRepository;
	public record Request( TodoList task ){}
	public record Response( String response ){}
	
	@Override
	public Response apply(Request request) {
		
		logger.info("Add task function called");
		
		if( request == null || request.task() == null) {
			return new Response( "Invalid request." );
		}
		
		TodoList task = request.task();
		
		logger.info("AddTaskService request : "+task.toString());
		
		task.setCompleted(false);
		if( task.getDeadline() == null ) {
			task.setDeadline(LocalDateTime.now().plusDays(1) );
		}
		
		if( validRequest( task ) ) {
			todoListRepository.save( task );
			logger.info("AddTaskService New Task added : "+task.getTask());
			return new Response( "Task added todo list." );
		}
		
		return new Response( "Insufficient information to create task." );
	}

	private boolean validRequest(TodoList task) {
		
		return Utils.isValidString( task.getTask() ) &&  
				(task.getDeadline().isEqual(LocalDateTime.now()) || task.getDeadline().isAfter(LocalDateTime.now())); 
	}

}