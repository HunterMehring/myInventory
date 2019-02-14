package org.launchcode.myInventory.models;

import org.launchcode.myInventory.models.data.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player implements Comparable<Player> {

    @Id//says this should be a primary key in the database
    @GeneratedValue//says that a value should be generated for us.
    private int id;

    @NotNull(message = "player must have a name")
    @Size(min = 3, max=15, message = "player must be between 3 and 15 characters")
    private String name;

    @NotNull(message = "player must have a number")
    @Min(0)
    @Max(100)
    private Integer number;

    private String grade;

    @NotNull
    private String safe;

    //this list of items will be populated based on the relationships we set up with our controllers
    @ManyToMany
    private List<Item> items = new ArrayList<>();

    @ManyToMany
    private List<Task> tasks = new ArrayList<>();

    private Long userId;

    public Player(){}

    public Player(String name, Integer number, String grade, long userId) {
        this.name = name;
        this.number = number;
        this.grade = grade;
        this.userId = userId;
    }

    public Player(String name, Integer number, String grade, String safe, long userId) {
        this.name = name;
        this.number = number;
        this.grade = grade;
        this.safe = safe;
        this.userId = userId;
    }

    //simply adds a given item to the list
    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);}

    public void addTask(Task task) {tasks.add(task);}

    public void removeTask(Task task) {tasks.remove(task);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(Player o) {
        int compare = name.compareTo(o.getName());
        return compare;
    }


}

