package com.example.ex1curs9.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String author;
    private double price;
    private int stock;
    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToMany(mappedBy = "books")
    private Set<Cart> carts = new HashSet<>();
    @ManyToMany
    private Set<Order> orders = new HashSet<>();

    public Book() {
    }

    public Book(String title, String author, double price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public Set<Order> getOrders(){
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


}