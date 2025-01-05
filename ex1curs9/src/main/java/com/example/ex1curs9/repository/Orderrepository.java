package com.example.ex1curs9.repository;

import com.example.ex1curs9.model.Order;
import com.example.ex1curs9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByOrderDateDesc(User user); // istoric comenzi


    List<Order> findAllByOrderByOrderDateDesc(); // admin - istoric comenzi


    @Query("SELECT SUM(b.price) FROM Order o JOIN o.books b WHERE o.id = :orderId")
    Double calculateOrderTotal(@Param("orderId") Long orderId); // calcul total comandă


    boolean existsByUserAndStatus(User user, Order.OrderStatus status); // verifica daca exista deja comanda


    Optional<Order> findFirstByUserOrderByOrderDateDesc(User user);

    // actualizare status comanda
    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :status WHERE o.id = :orderId")
    void updateOrderStatus(@Param("orderId") Long orderId, @Param("status") Order.OrderStatus status);
}