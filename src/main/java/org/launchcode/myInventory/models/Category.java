package org.launchcode.myInventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

// make this items category then make a task category...
@Entity//makes sure that this class will be mapped to a relational database table(persistent)
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    //@Column(unique = true) why not working?
    @Size(min = 2, max = 15, message = "category must be between 2 and 15 characters")//need to be able to make it longer
    private String name;

    @OneToMany//each category can have many items
    @JoinColumn(name = "category_id")//used to determine which garment belongs to a given category.
    private List<Item> items = new ArrayList<>();

    private Long userId;

    public Category() {}

    public Category(long userId) {
        this.userId = userId;
    }

    public Category(String name, Long userId) {this.name = name; this.userId = userId;}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  List<Item> getItem() {return items;}

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}