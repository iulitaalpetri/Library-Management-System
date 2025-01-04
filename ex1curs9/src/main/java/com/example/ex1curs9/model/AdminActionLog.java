package com.example.ex1curs9.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AdminActionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User adminUser;

    @Column(nullable = false)
    private String actionDescription;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public AdminActionLog() {
        this.timestamp = LocalDateTime.now();
    }

    public AdminActionLog(User adminUser, String actionDescription) {
        this();  // pentru a seta timestamp-ul
        if (adminUser.getRole() != User.Role.ADMIN) {
            throw new IllegalArgumentException("User must be an admin to create action logs");
        }
        this.adminUser = adminUser;
        this.actionDescription = actionDescription;
    }


    public long getId() {
        return id;
    }

    public User getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(User adminUser) {
        if (adminUser.getRole() != User.Role.ADMIN) {
            throw new IllegalArgumentException("User must be an admin to be set as adminUser");
        }
        this.adminUser = adminUser;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}