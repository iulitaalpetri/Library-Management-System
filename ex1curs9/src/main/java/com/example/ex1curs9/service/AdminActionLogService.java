package com.example.ex1curs9.service;

import com.example.ex1curs9.model.AdminActionLog;
import com.example.ex1curs9.model.User;
import com.example.ex1curs9.repository.AdminActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminActionLogService {
    private final AdminActionLogRepository adminActionLogRepository;

    @Autowired
    public AdminActionLogService(AdminActionLogRepository adminActionLogRepository) {
        this.adminActionLogRepository = adminActionLogRepository;
    }


    public AdminActionLog create(User admin, String actionDescription) {
        if (admin.getRole() != User.Role.ADMIN) {
            throw new IllegalArgumentException("Only admin users can create action logs");
        }

        AdminActionLog log = new AdminActionLog(admin, actionDescription);
        return adminActionLogRepository.save(log);
    }


    public List<AdminActionLog> findAllBookActions() {
        return adminActionLogRepository.findAllBookActions();
    }


    public List<AdminActionLog> findBookActions(Long bookId, String action) {
        return adminActionLogRepository.findBookActionsByBookId(bookId, action);
    }


    public List<AdminActionLog> findAllOrderViewActions() {
        return adminActionLogRepository.findAllOrderViewActions();
    }


    public Optional<AdminActionLog> findLastActionForBook(Long bookId) {
        return adminActionLogRepository.findLastActionForBook(bookId);
    }


    public List<AdminActionLog> findRecentActionsByAdmin(User admin, LocalDateTime since) {
        if (admin.getRole() != User.Role.ADMIN) {
            throw new IllegalArgumentException("Can only search actions for admin users");
        }
        return adminActionLogRepository.findRecentActionsByAdmin(admin, since);
    }


    public void logBookAction(User admin, Long bookId, String action, String details) {
        String actionDescription = String.format("BOOK_%s_%d_%s", action, bookId, details);
        create(admin, actionDescription);
    }


    public void logOrderView(User admin) {
        String actionDescription = String.format("ORDER_VIEW_%s", LocalDateTime.now());
        create(admin, actionDescription);
    }
}