package com.example.ex1curs9.controller;

import com.example.ex1curs9.annotations.RequireAdmin;
import com.example.ex1curs9.dto.BookDto;
import com.example.ex1curs9.dto.CategoryDto;
import com.example.ex1curs9.mapper.BookMapper;
import com.example.ex1curs9.mapper.CategoryMapper;
import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Category;
import com.example.ex1curs9.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final BookMapper bookMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper, BookMapper bookMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    @RequireAdmin
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category savedcategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.toDto(savedcategory));
    }

    // get all books from a category
    @GetMapping("/{id}")
    public ResponseEntity<List<BookDto>> getBooksInCategory(@PathVariable Long id) {
        List<Book> books = categoryService.getBooksInCategory(id);
        return ResponseEntity.ok(bookMapper.toDtoList(books));
    }




}
