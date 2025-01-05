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
    private final AdminActionLogService adminActionLogService;

    @Autowired
    public BookService(BookRepository bookRepository, AdminActionLogService adminActionLogService) {
        this.bookRepository = bookRepository;
        this.adminActionLogService = adminActionLogService;
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

    public void updateBook(Long id, String title, String author, double price) {
        bookRepository.updateBookDetails(id, title, author, price);
    }

    public void updateBookStock(Long bookId, int quantity) {
        bookRepository.updateStock(bookId, quantity);
    }

    public void deleteBook(Long id) {
        if(bookRepository.existsByIdAndStockEquals(id, 0)) {
            bookRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Cannot delete book with existing stock");
        }
    }

    public List<Book> getBooksInStock() {
        return bookRepository.findByStockGreaterThan(0);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}
