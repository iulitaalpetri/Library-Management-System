package com.example.ex1curs9.service;

import com.example.ex1curs9.exception.CategoryAlreadyExistsException;
import com.example.ex1curs9.exception.CategoryNotFoundException;
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

    public Category getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            throw new CategoryNotFoundException();
        }
        return category;
    }

    public List<Category> getAllCategoriesSorted() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    public List<Category> getNonEmptyCategories() {
        return categoryRepository.findNonEmptyCategories();
    }

    public List<Category> getBooksByCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException();
        }
        return categoryRepository.findBooksByCategoryId(categoryId);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!existingCategory.getName().equals(updatedCategory.getName()) &&
                categoryRepository.existsByName(updatedCategory.getName())) {
            throw new CategoryAlreadyExistsException();
        }

        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException();
        }
        categoryRepository.deleteById(id);
    }
}