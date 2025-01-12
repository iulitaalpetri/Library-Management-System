package com.example.ex1curs9.service;

import com.example.ex1curs9.exception.CategoryAlreadyExistsException;
import com.example.ex1curs9.exception.CategoryNotFoundException;
import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Category;
import com.example.ex1curs9.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException();
        }
        return categoryRepository.save(category);
    }



    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException());
    }

    public List<Book> getBooksInCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        return category.getBooks();
    }

}