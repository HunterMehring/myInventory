package org.launchcode.myInventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Item implements Comparable<Item>{

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "items")
    private List<Player> players;

    //make number an int so you can sort by number
    @NotNull
    private String number;

    @NotNull
    @Size(min = 1, message = "item must have a size")
    private String size;

    @NotNull
    private String other;

    @NotNull
    private boolean inInventory = true;

    public Item(Category category, String number, String size, String other) {
        this.category = category;
        this.number = number;// make a dropdown of all available numbers after selected category
        this.size = size;
        this.other = other;// make size after category, then have the dropdown for available numbers in that size.
    }

    public Item(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public boolean isInventory() {
        return inInventory;
    }
    public void setisInventory(boolean in) {
        this.inInventory = in;
    }

    @Override
    public int compareTo(Item o){
        int compare = category.getName().compareTo(o.getCategory().getName());
        if (compare == 0) {
            compare = size.compareTo(o.getSize());
        }
        return compare;
    }


}