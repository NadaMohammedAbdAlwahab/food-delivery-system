package com.fooddelivery.restaurantservice.model;

import java.math.BigDecimal;

public class MenuItem {
    private String name;
    private BigDecimal price;

    public MenuItem() {}

    public MenuItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
