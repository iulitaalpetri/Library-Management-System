package com.example.ex1curs9.repository;

import com.example.ex1curs9.model.Order;
import com.example.ex1curs9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByStatus(Order.OrderStatus status);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByTotalPriceGreaterThan(double price);
    List<Order> findByUserAndStatus(User user, Order.OrderStatus status);
    List<Order> findAllByOrderByOrderDateDesc();

    @Query("SELECT o FROM Order o JOIN o.books b WHERE b.id = :bookId")
    List<Order> findOrdersContainingBook(@Param("bookId") Long bookId);

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.user.id = :userId")
    Double calculateTotalSpentByUser(@Param("userId") Long userId);

    @Query("SELECT o.status, COUNT(o) FROM Order o GROUP BY o.status")
    List<Object[]> countOrdersByStatus();
}