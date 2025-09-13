package com.fooddelivery.restaurantservice.controller;

import com.fooddelivery.restaurantservice.model.MenuItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    @GetMapping("/menu")
    public List<MenuItem> getMenu() {
        return Arrays.asList(
                new MenuItem("Burger", new BigDecimal("120.00")),
                new MenuItem("Fries", new BigDecimal("50.00")),
                new MenuItem("Cola", new BigDecimal("25.00")),
                new MenuItem("Pizza Slice", new BigDecimal("80.00"))
        );
    }

    @GetMapping("/health")
    public String health() {
        return "restaurant-service: OK";
    }
}
