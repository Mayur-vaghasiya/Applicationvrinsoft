package com.example.applicationvrinsoft.model;

import java.io.Serializable;

public class ProductMast implements Serializable {
    private String name;
    private int price;
    private String category;

    public ProductMast(String name, int price, String category) {
        this.name=name;
        this.price=price;
        this.category=category;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }




}
