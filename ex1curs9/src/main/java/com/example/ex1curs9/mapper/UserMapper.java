package com.example.ex1curs9.mapper;

import com.example.ex1curs9.dto.UserDto;
import com.example.ex1curs9.model.Cart;
import com.example.ex1curs9.model.Order;
import com.example.ex1curs9.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());

        dto.setRole(user.getRole());


        if (user.getOrders() != null) {
            dto.setOrders(user.getOrders().stream()
                    .map(order -> {
                        Order simpleOrder = new Order();
                        simpleOrder.setId(order.getId());
                        return simpleOrder;
                    })
                    .collect(Collectors.toList()));
        }

        if (user.getCart() != null) {
            Cart simpleCart = new Cart();
            simpleCart.setId(user.getCart().getId());
            dto.setCart(simpleCart);
        }


        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        if (dto.getPassword() != null) {

            user.setPassword(dto.getPassword());
        }
        user.setRole(dto.getRole());


        return user;
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(UserDto dto, User user) {
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {

            user.setPassword(dto.getPassword());
        }
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }

    }
}