package org.launchcode.myInventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

// make this items category then make a task category...
//why doesnt it like things that are only 3 letters long
@Entity//makes sure that this class will be mapped to a relational database table(persistent)
public class Task {

    @Id //JPA annotations
    @GeneratedValue
    private int id;

    @NotNull
    //@Column(unique = true) why not working?
    @Size(min = 3, max = 15)
    private String name;

    @ManyToMany(mappedBy = "tasks")
    private List<Player> players;

    public Task() {}

    public Task(String name) { this.name = name;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }
}