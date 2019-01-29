package org.launchcode.myInventory.models;

import org.launchcode.myInventory.models.data.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player implements Comparable<Player> {

    @Id//says this should be a primary key in the database
    @GeneratedValue//says that a value should be generated for us.
    private int id;

    @NotNull
    @Size(min = 3, max=15)
    private String name;

    @NotNull
    @Size(min = 1, message = "each player must have a number")
    private String number;

    @NotNull
    @Size(min = 1, message = "each player must have a grade")
    private String grade;

    @NotNull
    private String safe;

    //this list of items will be populated based on the relationships we set up with our controllers
    //there will be many cheeses associated with many menus
    //This is going to be populated with a list of cheeses that are present for each Player
    //Hibernate figures out the relationship between cheeses and
    // menus
    @ManyToMany
    private List<Item> items = new ArrayList<>();

    @ManyToMany
    private List<Task> tasks = new ArrayList<>();

    public Player(){}

    public Player(String name, String number, String grade) {
        this.name = name;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    @Override
    public int compareTo(Player o) {
        int compare = name.compareTo(o.getName());
        return compare;
    }


}

