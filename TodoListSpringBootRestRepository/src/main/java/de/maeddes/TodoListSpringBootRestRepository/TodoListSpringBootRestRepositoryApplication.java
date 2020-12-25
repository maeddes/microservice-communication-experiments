package de.maeddes.TodoListSpringBootRestRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TodoListSpringBootRestRepositoryApplication {

	@Autowired
	TodoRestRepository todoRepository;

	@GetMapping("/todos")
	List<Todo> all(){

		List<Todo> todos = new ArrayList<Todo>();
		todoRepository.findAll().forEach(todos::add);
		return todos;
	}

	@PostMapping("/todos/{newTodo}")
	String addTodo(@PathVariable String newTodo){

		Todo todo = new Todo(newTodo);
		todoRepository.save(todo);
		return todo+" saved";

	}

	@GetMapping

	public static void main(String[] args) {
		SpringApplication.run(TodoListSpringBootRestRepositoryApplication.class, args);
	}

}
