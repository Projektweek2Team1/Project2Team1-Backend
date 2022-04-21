package com.example.demo;

import com.example.demo.database.service.AppService;

import java.sql.Array;
import java.sql.Date;
import java.sql.Timestamp;

public class Order {

    private int id;
    private String email;
    private Timestamp date;
    private Integer[] itemIds;

    public Order() {}

    public Order(int id, String email, Timestamp date, Integer[] itemIds) {
        super();
        this.id = id;
        this.email = email;
        this.date = date;
        this.itemIds = itemIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer[] getItemIds() {
        Integer[] newArray = new Integer[itemIds.length];
        for (int i = 0; i < itemIds.length; i++) {
            newArray[i] = itemIds[i];
        }
        return newArray;
    }

    public void setItemIds(Integer[] itemIds) {
        this.itemIds = itemIds;
    }

    public void decreaseQuantities(AppService service) {
        for (Integer itemId : itemIds) {
            service.getProductService().decreaseQuantity(itemId);
        }
    }

}
