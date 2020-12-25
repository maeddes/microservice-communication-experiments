package de.maeddes.TodoListSpringBootRestRepository;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Todo {

    @Id
    private String name;
    private int priority;

    public Todo(){

        priority = 2;
    }

    public Todo(String name){

        this.name = name;
        priority = 2;

    }
    
}
