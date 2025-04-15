package com.example.OrderMicroservice.service;

import com.example.OrderMicroservice.dto.OrderLineItemsDto;
import com.example.OrderMicroservice.dto.OrderRequest;
import com.example.OrderMicroservice.model.Order;
import com.example.OrderMicroservice.model.OrderLineItems;
import com.example.OrderMicroservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems().stream().map(this::mapToDTO).toList();
        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
