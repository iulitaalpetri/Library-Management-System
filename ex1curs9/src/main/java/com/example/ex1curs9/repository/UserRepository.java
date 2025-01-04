package com.example.ex1curs9.repository;

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
    List<User> findByOrdersIsNotEmpty();
    @Query("SELECT DISTINCT u FROM User u JOIN u.orders o WHERE o.status = :status")
    List<User> findUsersWithOrderStatus(@Param("status") Order.OrderStatus status);
    @Query("SELECT DISTINCT u FROM User u JOIN u.adminActions a WHERE u.role = 'ADMIN' AND a.timestamp BETWEEN :startDate AND :endDate")
    List<User> findAdminsWithActionsBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
    @Query("SELECT u.role, COUNT(u) FROM User u GROUP BY u.role")
    List<Object[]> countUsersByRole();
}