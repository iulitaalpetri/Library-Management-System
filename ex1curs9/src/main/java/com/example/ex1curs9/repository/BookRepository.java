package com.example.ex1curs9.repository;

import com.example.ex1curs9.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // query urile sunt generate automat de Spring Data JPA pe baza numelui metodelor in urmatorul mod:
    // findBy + numele campului dupa care se face cautarea + criteriul de cautare

    List<Book> findByTitleContaining(String title); // cautare dupa titlu

    List<Book> findByStockGreaterThan(int stock); // verificare stoc inainte de cos sau order

    @Modifying
    @Query("UPDATE Book b SET b.stock = b.stock - :quantity WHERE b.id = :bookId AND b.stock >= :quantity")
    @Transactional
    int updateStock(@Param("bookId") Long bookId, @Param("quantity") int quantity); // actualizare stoc cand se plaseaza comanda


    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.title = :title, b.author = :author, b.price = :price WHERE b.id = :id")
    void updateBookDetails(
            @Param("id") Long id,
            @Param("title") String title,
            @Param("author") String author,
            @Param("price") double price
    ); // admin - actualizare detalii carte



}
