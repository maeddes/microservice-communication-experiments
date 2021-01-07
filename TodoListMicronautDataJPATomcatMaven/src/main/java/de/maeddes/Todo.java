package de.maeddes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.micronaut.core.annotation.Introspected;


@Entity
@Introspected
@Table(name = "todo")
public class Todo {

    @Id
    String name;
    int priority;

    public Todo(){
    }

    public Todo(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
}
