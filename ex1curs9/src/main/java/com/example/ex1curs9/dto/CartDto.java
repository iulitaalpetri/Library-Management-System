package com.example.ex1curs9.dto;

import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.User;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class CartDto {

    private Long id;
    private Long userId;
    private Set<BookDto> books = new HashSet<>();
    public CartDto(){
    }

    public CartDto(Long id, Long userId, Set<BookDto> books) {
        this.id = id;
        this.userId = userId;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBooks(Set<BookDto> collect) {
        this.books = collect;
    }
}
