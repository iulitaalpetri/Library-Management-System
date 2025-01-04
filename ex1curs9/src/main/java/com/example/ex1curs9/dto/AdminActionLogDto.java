package com.example.ex1curs9.dto;

import com.example.ex1curs9.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class AdminActionLogDto {

    private User adminUser;
    private String actionDescription;
    private LocalDateTime timestamp;

    public AdminActionLogDto() {
        this.timestamp = LocalDateTime.now();
    }

    public AdminActionLogDto(User adminUser, String actionDescription) {
        this();  // pentru a seta timestamp-ul
        if (adminUser.getRole() != User.Role.ADMIN) {
            throw new IllegalArgumentException("User must be an admin to create action logs");
        }
        this.adminUser = adminUser;
        this.actionDescription = actionDescription;
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
