package com.example.ex1curs9.repository;
import com.example.ex1curs9.model.AdminActionLog;
import com.example.ex1curs9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdminActionLogRepository extends JpaRepository<AdminActionLog, Long>{
    List<AdminActionLog> findByAdminUser(User adminUser); // log uri pentru un admin
    List<AdminActionLog> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate); // loguri intre 2 date
    List<AdminActionLog> findByActionDescriptionContaining(String description); // loguri cu o anumita descriere
    List<AdminActionLog> findAllByOrderByTimestampDesc(); // cele mai recente loguri ordonate dupa timestamp descrescator
    List<AdminActionLog> findByAdminUserAndTimestampBetween( // loguri pentru un admin intre 2 date
            User adminUser,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
    List<AdminActionLog> findByAdminUserAndActionDescriptionContaining( // loguri pentru un admin cu o anumita descriere
    User adminUser,
    String description
    );

}
