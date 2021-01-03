package de.maeddes;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(hal = true, path = "todos")
public interface TodoResource extends PanacheEntityResource<Todo, String>{
    
}
