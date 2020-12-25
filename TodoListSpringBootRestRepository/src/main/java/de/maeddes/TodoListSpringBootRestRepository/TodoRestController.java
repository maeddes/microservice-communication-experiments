package de.maeddes.TodoListSpringBootRestRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/todos")
public class TodoRestController {

	@Autowired
	TodoRestRepository todoRepository;

    @GetMapping
	List<Todo> all(){

		List<Todo> todos = new ArrayList<Todo>();
		todoRepository.findAll().forEach(todos::add);
		return todos;
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	Todo addTodo(@RequestBody Todo todo){

		todoRepository.save(todo);
		return todo;

	}

	@PostMapping("/{name}")
	Todo addTodo(@PathVariable String name){

		Todo todo = new Todo(name);
		todoRepository.save(todo);
		return todo;

	}
	
	@PostMapping("/{name}/{priority}")
	Todo addTodo(@PathVariable String name, @PathVariable int priority){

		Todo todo = new Todo(name,priority);
		todoRepository.save(todo);
		return todo;

	}
    
}
