package com.fooddelivery.orderservice.controller;

import com.fooddelivery.orderservice.model.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderRequest request) {
        // احضار قائمة الأسعار من خدمة المطاعم
        String menuUrl = "http://localhost:8081/api/menu";
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> menu = restTemplate.getForObject(menuUrl, List.class);

        Map<String, BigDecimal> priceMap = new HashMap<>();
        if (menu != null) {
            for (Map<String, Object> m : menu) {
                String name = (String) m.get("name");
                BigDecimal price = new BigDecimal(String.valueOf(m.get("price")));
                priceMap.put(name, price);
            }
        }

        BigDecimal total = BigDecimal.ZERO;
        List<Map<String, Object>> itemsDetail = new ArrayList<>();

        for (String item : request.getItems()) {
            BigDecimal price = priceMap.getOrDefault(item, BigDecimal.ZERO);
            total = total.add(price);
            Map<String, Object> detail = new HashMap<>();
            detail.put("name", item);
            detail.put("price", price);
            itemsDetail.add(detail);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("customerName", request.getCustomerName());
        response.put("items", itemsDetail);
        response.put("total", total);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public String health() {
        return "order-service: OK";
    }
}
