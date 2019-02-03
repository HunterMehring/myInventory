package org.launchcode.myInventory.models.forms;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class SearchItemForm {

    @Id
    @GeneratedValue
    private int id;

    //make number an int so you can sort by number
    @NotNull
    private Integer number;

    @NotNull
    private String size;

    public SearchItemForm(){
    }

    public SearchItemForm(Integer number, String size){
        this.number = number;
        this.size = size;
    }

    public int getId() {
        return id;
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
}
