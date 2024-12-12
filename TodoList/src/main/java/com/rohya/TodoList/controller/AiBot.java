package com.rohya.TodoList.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin("*")
@RequestMapping("/askAi")
public class AiBot {

	private ChatClient chatClient;
	private ChatClient.Builder builder;
	@Value("${system.build.message}")
	private String systemConfig;
	
	public AiBot( ChatClient.Builder builder ) {
		this.builder = builder;
	}
	
	@PostConstruct
	public void AiBotInit( ) {
		chatClient = builder.defaultSystem(systemConfig)
				.defaultFunctions( "addTaskFunction","showAllTasksFunction" )
				.build();	
	}
	
	@GetMapping("/")
	public String askAi( @RequestParam String askme ) {
		
		return chatClient.prompt()
				.user(askme)
				.call()
				.content();
	}
}
