package com.example.ex1curs9.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.example.ex1curs9.model.User;
import com.example.ex1curs9.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    List<User> findByRole(User.Role role);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.orders WHERE u.id = :userId")
    Optional<User> findUserWithOrders(@Param("userId") Long userId);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.orders")
    List<User> findAllWithOrders();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.cart WHERE u.id = :userId")
    Optional<User> findUserWithCart(@Param("userId") Long userId);

    // nr de utilizatori cu un anumit rol
    Long countByRole(User.Role role);

}