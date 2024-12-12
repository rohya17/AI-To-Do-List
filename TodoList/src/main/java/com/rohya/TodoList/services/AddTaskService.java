package com.rohya.TodoList.services;

import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;

import com.rohya.TodoList.model.TodoList;
import com.rohya.TodoList.repository.TodoListRepository;
import com.rohya.TodoList.utils.Utils;

public class AddTaskService implements Function<AddTaskService.Request, AddTaskService.Response> {
	
	@Autowired private TodoListRepository todoListRepository;

	@Override
	public Response apply(Request request) {
		
		if( request == null || request.task() == null) {
			return new Response( "Invalid request." );
		}
		TodoList task = request.task();
		task.setCompleted(false);
		if( task.getDeadline() == null ) {
			task.setDeadline(LocalDate.now().plusDays(1) );
		}
		
		if( validRequest( task ) ) {
			todoListRepository.save( task );
			return new Response( "Task added todo list." );
		}
		
		return new Response( "Insufficient information to create task." );
	}

	private boolean validRequest(TodoList task) {
		
		return Utils.isValidString( task.getName() ) && 
				Utils.isValidString( task.getDescription() ) && 
				(task.getDeadline().isEqual(LocalDate.now()) || task.getDeadline().isAfter(LocalDate.now())); 
	}
	
	public record Request( TodoList task ){}
	public record Response( String response ){}

}