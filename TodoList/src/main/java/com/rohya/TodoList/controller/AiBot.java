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

@RestController
@CrossOrigin("*")
@RequestMapping("/askAi")
public class AiBot {

	private ChatClient chatClient;
	
	@Value("${system.build.message}")
	private String systemConfig;
	
	public AiBot( ChatClient.Builder builder ) {
		chatClient = builder.defaultFunctions( "addTaskFunction" ).defaultAdvisors(
				// Chat memory helps us keep context when using the chatbot for up to 100 previous messages.
				new MessageChatMemoryAdvisor(new InMemoryChatMemory(), "User", 100), // CHAT MEMORY
				new SimpleLoggerAdvisor()
		)
		.build();
	}
	
	@GetMapping("/")
	public String askAi( @RequestParam String askme ) {
		
		return chatClient.prompt()
				.system(systemConfig)
				.user(askme)
				.call()
				.content();
	}
}
