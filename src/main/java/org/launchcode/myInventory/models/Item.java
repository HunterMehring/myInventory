package org.launchcode.myInventory.models;

import javax.persistence.*;
import javax.validation.Valid;
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

    //sort by number
    @NotNull
    private Integer number;

    @NotNull
    private String size;

    @NotNull
    private String other;

    @NotNull
    private boolean inInventory = true;

    private Long userId;

    public Item(Category category, Integer number, String size, String other, long userId) {
        this.category = category;
        this.number = number;
        this.size = size;
        this.other = other;
        this.userId = userId;
    }

    public Item(Category category, Integer number, String size, long userId) {
        this.category = category;
        this.number = number;
        this.size = size;
        this.userId = userId;
    }

    public Item(){}

    public Item(long userId) {
        this.userId = userId;
    }

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
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

    public List<Player> getPlayers() {
        return players;
    }

    //make a function to remove players

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
