package com.example.ex1curs9.dto;

import com.example.ex1curs9.model.Cart;
import com.example.ex1curs9.model.Order;
import com.example.ex1curs9.model.User;

import java.util.List;

public class UserDto {
    private String username;
    private String password;
    private User.Role role;
    private List<Order> orders;
    private Cart cart;


    public UserDto() {
    }

    public UserDto(String username, String password, User.Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
