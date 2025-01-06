package com.example.ex1curs9.mapper;

import com.example.ex1curs9.dto.OrderDto;
import com.example.ex1curs9.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final BookMapper bookMapper;
    private final UserMapper userMapper;

    @Autowired
    public OrderMapper(BookMapper bookMapper, UserMapper userMapper) {
        this.bookMapper = bookMapper;
        this.userMapper = userMapper;
    }

    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setUser(order.getUser());
        dto.setBooks(order.getBooks());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        return dto;
    }

    public Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setUser(dto.getUser());
        order.setBooks(dto.getBooks());
        order.setTotalPrice(dto.getTotalPrice());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
        return order;
    }

    public List<OrderDto> toDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(OrderDto dto, Order order) {
        order.setUser(dto.getUser());
        order.setBooks(dto.getBooks());
        order.setTotalPrice(dto.getTotalPrice());
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());
    }
}