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
public interface BookRepository  extends JpaRepository<Book, Long>{
    // jpa creaza automat query-ul, book = entitatea, ling  = tip id
    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorAndPriceLessThan(String author, double price);

    @Modifying
    @Query("update Book b set b.stock = b.stock - :quantity where b.id = :bookId")
    @Transactional
    void updateStock(@Param("bookId") Long bookId, @Param("quantity") int quantity);
    List<Book> findByStockGreaterThan(int minStock);

}
