package com.example.ex1curs9.dto;

import com.example.ex1curs9.model.Book;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CategoryDto {
    private String name;
    private String description;

    public CategoryDto() {
    }

    public CategoryDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
