package com.example.ex1curs9.dto;

import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Order;
import com.example.ex1curs9.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class OrderDto {
    private Long userId;
    private Set<BookDto> books = new HashSet<>();
    private LocalDateTime orderDate;


    public OrderDto() {
        this.orderDate = LocalDateTime.now();
    }

    public OrderDto(long userId, Set<BookDto> books, double totalPrice) {
        this();
        this.userId = userId;
        this.books = books;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<BookDto> getBooks() {
        return books;
    }



    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }



    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
