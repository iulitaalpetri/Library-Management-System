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

    @Autowired
    public OrderMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setUserId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setBooks(order.getBooks().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toSet()));
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }


    public List<OrderDto> toDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}