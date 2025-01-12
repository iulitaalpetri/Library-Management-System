package com.example.ex1curs9.controller;

import com.example.ex1curs9.annotations.RequireAdmin;
import com.example.ex1curs9.dto.BookDto;
import com.example.ex1curs9.mapper.BookMapper;
import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Category;
import com.example.ex1curs9.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;


    @Autowired
    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }


    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(bookMapper.toDtoList(books));
    }


    @GetMapping("/search/{title}")
    public ResponseEntity<List<BookDto>> searchBooks(@PathVariable String title) {
        List<Book> books = bookService.searchByTitle(title);
        return ResponseEntity.ok(bookMapper.toDtoList(books));
    }



    @PostMapping
    @RequireAdmin
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) {

        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookMapper.toDto(savedBook));
    }


    @GetMapping("/in-stock")
    public ResponseEntity<List<BookDto>> getBooksInStock() {
        List<Book> books = bookService.getBooksInStock();
        return ResponseEntity.ok(bookMapper.toDtoList(books));
    }



}
