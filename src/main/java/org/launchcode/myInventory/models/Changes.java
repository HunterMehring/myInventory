package org.launchcode.myInventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity//makes sure that this class will be mapped to a relational database table(persistent)
public class Changes {

    @Id //JPA annotations
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 2, max = 50, message = "change must be between 2 and 50 characters")
    private String type;

    private String name;

    private String item;

    private String status;

    private Integer number;

    private Date myDate;

    public Changes() {

    }

    public Changes(Date myDate) {
        this.myDate = new Date();
    }

    public Changes(String type, String item, String status) {
        this.type = type;
        this.item = item;
        this.status = status;
        this.myDate = new Date();
    }

    public Changes(String type, String item, String status, Integer number) {
        this.type = type;
        this.item = item;
        this.status = status;
        this.number = number;
        this.myDate = new Date();
    }

    public Changes(String type, String name, String item, String status, Integer number) {
        this.type = type;
        this.name = name;
        this.item = item;
        this.status = status;
        this.number = number;
        this.myDate = new Date();
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }


}