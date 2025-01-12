package com.example.ex1curs9.service;

import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void updateBookStock(Long bookId, int quantity) {
        bookRepository.updateStock(bookId, quantity);
    }


    public List<Book> getBooksInStock() {
        return bookRepository.findByStockGreaterThan(0);
    }

}
