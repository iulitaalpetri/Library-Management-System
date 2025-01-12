package com.example.ex1curs9.mapper;

import com.example.ex1curs9.dto.CartDto;
import com.example.ex1curs9.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    private final BookMapper bookMapper;

    @Autowired
    public CartMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public CartDto toDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setBooks(cart.getBooks().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toSet()));
        return dto;
    }
}