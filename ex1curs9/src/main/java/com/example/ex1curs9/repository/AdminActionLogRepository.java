package com.example.ex1curs9.repository;
import com.example.ex1curs9.model.AdminActionLog;
import com.example.ex1curs9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdminActionLogRepository extends JpaRepository<AdminActionLog, Long> {
    @Query("SELECT a FROM AdminActionLog a WHERE a.actionDescription LIKE 'BOOK%' ORDER BY a.timestamp DESC")
    List<AdminActionLog> findAllBookActions(); // actiuni pe carti


    @Query("SELECT a FROM AdminActionLog a WHERE a.actionDescription LIKE CONCAT('BOOK_', :action, '_%', :bookId) ORDER BY a.timestamp DESC")
    List<AdminActionLog> findBookActionsByBookId(@Param("bookId") Long bookId, @Param("action") String action);


    @Query("SELECT a FROM AdminActionLog a WHERE a.actionDescription LIKE 'ORDER_VIEW%' ORDER BY a.timestamp DESC")
    List<AdminActionLog> findAllOrderViewActions();

    @Query("SELECT a FROM AdminActionLog a WHERE a.actionDescription LIKE CONCAT('BOOK_%', :bookId) ORDER BY a.timestamp DESC")
    Optional<AdminActionLog> findLastActionForBook(@Param("bookId") Long bookId);

    @Query("SELECT a FROM AdminActionLog a WHERE a.adminUser = :admin AND a.timestamp >= :since ORDER BY a.timestamp DESC")
    List<AdminActionLog> findRecentActionsByAdmin(@Param("admin") User admin, @Param("since") LocalDateTime since);
}