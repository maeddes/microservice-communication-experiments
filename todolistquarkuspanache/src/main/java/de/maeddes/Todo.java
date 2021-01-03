package de.maeddes;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Todo extends PanacheEntityBase{

    @Id
    public String name;
    public int priority;
    
}
