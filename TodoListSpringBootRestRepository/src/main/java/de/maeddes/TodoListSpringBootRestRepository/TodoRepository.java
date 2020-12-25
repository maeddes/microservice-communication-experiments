package de.maeddes.TodoListSpringBootRestRepository;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo,String>{
    
}
