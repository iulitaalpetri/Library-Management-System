package com.example.ex1curs9.dto;

import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.User;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class CartDto {
    private User user;
    private Set<Book> books = new HashSet<>();

    public CartDto(){

    }

    public CartDto(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Book> getBooks(){
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        books.add(book);
    }
}
