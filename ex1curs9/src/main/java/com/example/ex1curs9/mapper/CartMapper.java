package com.example.ex1curs9.mapper;

import com.example.ex1curs9.dto.CartDto;
import com.example.ex1curs9.model.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartDto toDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setUser(cart.getUser());
        dto.setBooks(cart.getBooks());
        return dto;
    }

    public Cart toEntity(CartDto dto) {
        Cart cart = new Cart();
        cart.setUser(dto.getUser());
        cart.setBooks(dto.getBooks());
        return cart;
    }

    public List<CartDto> toDtoList(List<Cart> carts) {
        return carts.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(CartDto dto, Cart cart) {
        cart.setUser(dto.getUser());
        cart.setBooks(dto.getBooks());
    }
}