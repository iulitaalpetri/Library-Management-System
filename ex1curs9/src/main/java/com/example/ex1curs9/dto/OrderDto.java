package com.example.ex1curs9.dto;

import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Order;
import com.example.ex1curs9.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class OrderDto {
    private User user;
    private Set<Book> books = new HashSet<>();
    private double totalPrice;
    private LocalDateTime orderDate;
    private Order.OrderStatus status;

    public OrderDto() {
        this.orderDate = LocalDateTime.now();
        this.status = Order.OrderStatus.PENDING;
    }

    public OrderDto(User user) {
        this();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Order.OrderStatus getStatus() {
        return status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(Order.OrderStatus status) {
        this.status = status;
    }
}
