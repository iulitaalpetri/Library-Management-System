package com.example.ex1curs9.mapper;
import com.example.ex1curs9.dto.CategoryDto;
import com.example.ex1curs9.model.Category;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    public Category toEntity(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    public List<CategoryDto> toDtoList(List<Category> categories) {
        return categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(CategoryDto dto, Category category) {
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
    }
}