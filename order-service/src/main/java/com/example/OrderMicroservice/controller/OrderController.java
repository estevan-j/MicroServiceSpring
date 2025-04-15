package com.example.OrderMicroservice.controller;

import com.example.OrderMicroservice.dto.OrderRequest;
import com.example.OrderMicroservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest order) {
        orderService.placeOrder(order);
        return "Order placed";
    }
}
