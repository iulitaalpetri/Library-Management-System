package com.example.ex1curs9.dto;

public class BookDto {
    private String title;
    private String author;
    private double price;
    private int stock;
    private long categoryId;

    public BookDto() {
    }

    public BookDto(String title, String author, double price, int stock, long categoryId) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
