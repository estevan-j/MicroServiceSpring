package com.example.OrderMicroservice.repository;

import com.example.OrderMicroservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
