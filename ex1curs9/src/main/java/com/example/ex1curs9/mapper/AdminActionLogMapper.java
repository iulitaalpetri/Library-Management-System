package com.example.ex1curs9.mapper;

import com.example.ex1curs9.dto.AdminActionLogDto;
import com.example.ex1curs9.model.AdminActionLog;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminActionLogMapper {

    public AdminActionLogDto toDto(AdminActionLog adminActionLog) {
        AdminActionLogDto dto = new AdminActionLogDto();
        dto.setAdminUser(adminActionLog.getAdminUser());
        dto.setActionDescription(adminActionLog.getActionDescription());
        dto.setTimestamp(adminActionLog.getTimestamp());
        return dto;
    }

    public AdminActionLog toEntity(AdminActionLogDto dto) {
        AdminActionLog adminActionLog = new AdminActionLog();
        adminActionLog.setAdminUser(dto.getAdminUser());
        adminActionLog.setActionDescription(dto.getActionDescription());
        adminActionLog.setTimestamp(dto.getTimestamp());
        return adminActionLog;
    }

    public List<AdminActionLogDto> toDtoList(List<AdminActionLog> logs) {
        return logs.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(AdminActionLogDto dto, AdminActionLog adminActionLog) {
        adminActionLog.setAdminUser(dto.getAdminUser());
        adminActionLog.setActionDescription(dto.getActionDescription());
        adminActionLog.setTimestamp(dto.getTimestamp());
    }
}