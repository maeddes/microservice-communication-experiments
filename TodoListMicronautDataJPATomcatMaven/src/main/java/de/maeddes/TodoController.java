package de.maeddes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.validation.Validated;

@Validated
@Controller("/todos")
public class TodoController{
    
    @Inject 
    TodoRepository repository;

    @Get 
    @Produces(MediaType.APPLICATION_JSON)
    List<Todo> getTodos() {

        List<Todo> todos = new ArrayList<Todo>();
        this.repository.findAll().forEach(todos::add);
        return todos;
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<Todo> save(@Body @Valid Todo todo) {

        return HttpResponse.created(repository.save(todo));
    }

    @Delete("/{name}")
    public HttpResponse<Todo> delete(String name) {

        this.repository.deleteById(name);
        return HttpResponse.noContent();

    }

}
