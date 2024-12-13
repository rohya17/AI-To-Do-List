package com.rohya.TodoList.config;

import java.util.List;
import java.util.function.Function;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.rohya.TodoList.services.AddTaskService;
import com.rohya.TodoList.services.ChangeTaskStatusService;
import com.rohya.TodoList.services.ShowTaskService;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ConfigClass {
	
	@Bean
    @Description("Add task to todo list")
    public Function<AddTaskService.Request, AddTaskService.Response> addTaskFunction() {
        return new AddTaskService();
    }
	
	@Bean
    @Description("Show all tasks")
    public Function<ShowTaskService.Request, ShowTaskService.Response> showAllTasksFunction() {
        return new ShowTaskService();
    }
	
	@Bean
    @Description("Update task status")
    public Function<ChangeTaskStatusService.Request, ChangeTaskStatusService.Response> changeTasksStatusFunction() {
        return new ChangeTaskStatusService();
    }
	
	@Bean
	public OpenAPI defineOpenApi() {
	   Server server = new Server();
	   server.setUrl("http://localhost:8080/");
	   server.setDescription("dev");

	   Contact myContact = new Contact();
	   myContact.setName("AI to do list");
	   myContact.setEmail("rohitthorave17895@gmail.com");

	   Info information = new Info()
	           .title( "API's documentation")
			   .version("1.0")
			   .description("This API exposes endpoints to related services." )
	           .contact(myContact);

	   return new OpenAPI()
			   .info(information)
			   .servers(List.of(server))
			   .components(new io.swagger.v3.oas.models.Components());
	}
}
