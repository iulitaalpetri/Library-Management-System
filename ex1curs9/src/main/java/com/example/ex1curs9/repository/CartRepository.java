package com.example.ex1curs9.repository;

import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);

    boolean existsByUserId(Long userId);

    @Query("SELECT COUNT(b) FROM Cart c JOIN c.books b WHERE c.id = :cartId")
    long countBooksByCartId(@Param("cartId") Long cartId);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Cart c JOIN c.books b WHERE c.id = :cartId AND b.id = :bookId")
    boolean existsBookInCart(@Param("cartId") Long cartId, @Param("bookId") Long bookId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.id = :cartId")
    void clearCart(@Param("cartId") Long cartId);
}
