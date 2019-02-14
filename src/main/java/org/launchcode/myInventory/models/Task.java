package org.launchcode.myInventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity//makes sure that this class will be mapped to a relational database table(persistent)
public class Task {

    @Id //JPA annotations
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    private Long userId;

    @ManyToMany(mappedBy = "tasks")
    private List<Player> players;

    public Task() {}

    public Task(String name, long userId) { this.name = name; this.userId = userId;}


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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}