package com.example.ex1curs9.repository;

import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId); // cosul unui user
    boolean existsByUserId(Long userId); // daca exista cos pentru un user
    List<Cart> findByBook(Book book); // cosuri care contin o anumita carte
    // Query personalizat pentru a număra câte cărți are un coș
    @Query("SELECT COUNT(b) FROM Cart c JOIN c.books b WHERE c.id = :cartId")
    long countBooksByCartId(@Param("cartId") Long cartId);


}
