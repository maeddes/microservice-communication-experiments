package de.maeddes.thymeleafwebclient;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController("play")
public class PlayAroundController {

    @Autowired
	SimpleAPIClient simpleAPIClient;

	@Autowired
	WebClient defaultWebClient;

	@GetMapping("/debug/{todo}")
	public void debugCall(@PathVariable String todo){

		String uri = "http://localhost:8080/todos/"+todo;
		System.out.println(uri);

		Mono<Todo> t = defaultWebClient
		.get()
		.uri(uri)
		.retrieve()
		.bodyToMono(Todo.class);
		
		System.out.println(t);

		Todo td = defaultWebClient
		.get()
		.uri(uri)
		.retrieve()
		.bodyToMono(Todo.class)
		.block();

		System.out.println(td);

	}

    @GetMapping("/helloo")
    public String sampleCall(){

        return WebClient
            .builder()
            .build()
            .get()
            .uri("http://jsonplaceholder.typicode.com/posts/1")
            .retrieve()
            .bodyToMono(JsonNode.class)
            .block()
            .toString();

    }

    @GetMapping("/hellooo")
    public String simpleCall(){

        return WebClient
            .create("http://jsonplaceholder.typicode.com/posts/1")
            .get()
            .retrieve()
            .bodyToMono(JsonNode.class)
            .block()
            .toString();

    }

	@GetMapping("/todos/{todo}")
	public Todo invokeClientWithGet(@PathVariable String todo){

		Todo todoObject = simpleAPIClient.getTodoFromAPI(todo);
		return todoObject;

	}

	@GetMapping("/todos")
	public Todo[] invokeClientWithGetAll(){

		Todo[] todoObject = simpleAPIClient.getTodosFromAPI();
		return todoObject;

	}

	@GetMapping("/testGetJSON")
	String invokeClientWithGetJSON(){

		JsonNode node = simpleAPIClient.getTodoJSONFromAPI();
		//node.subscribe(System.out::println);
		System.out.println("to String "+node.toString());
		System.out.println("as Text "+node.asText());
		return node.asText();

	}

	@GetMapping("/testPostString/{todo}")
	Todo invokeClientWithPostString(@PathVariable String todo){

		return simpleAPIClient.postToTodoAPI(todo);

	}

	@GetMapping("/testPostObject/{todo}")
	Todo invokeClientWithPostObject(@PathVariable String todo){

		return simpleAPIClient.postToTodoAPI(new Todo(todo));

	}

	@GetMapping("/testDelete/{todo}")
	Void invokeClientWithDeleteString(@PathVariable String todo){

		return simpleAPIClient.deleteTodoFromAPI(todo);

	}
    
}
