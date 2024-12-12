package com.rohya.TodoList.config;

import java.util.List;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.rohya.TodoList.services.AddTaskService;

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
	public OpenAPI defineOpenApi() {
	   Server server = new Server();
	   server.setUrl("http://localhost:8080/");
	   server.setDescription("dev");

	   Contact myContact = new Contact();
	   myContact.setName("InnoAppSupport");
	   myContact.setEmail("innoappsupport@innominds.com");

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