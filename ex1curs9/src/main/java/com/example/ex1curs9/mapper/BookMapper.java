package com.example.ex1curs9.mapper;

import com.example.ex1curs9.dto.BookDto;
import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Category;
import com.example.ex1curs9.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setStock(book.getStock());
        dto.setCategoryId(book.getCategory().getId());
        return dto;
    }


    public Book toEntity(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setStock(dto.getStock());


        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + dto.getCategoryId()));
        book.setCategory(category);

        return book;
    }

    public List<BookDto> toDtoList(List<Book> books) {
        return books.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(BookDto dto, Book book, Category category) {
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setStock(dto.getStock());
        book.setCategory(category);
    }
}
