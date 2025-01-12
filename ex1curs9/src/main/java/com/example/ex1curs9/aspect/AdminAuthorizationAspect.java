package com.example.ex1curs9.aspect;

import com.example.ex1curs9.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.example.ex1curs9.annotations.RequireAdmin;
import com.example.ex1curs9.exception.UnauthorizedException;
import com.example.ex1curs9.model.User;


@Aspect
@Component
public class AdminAuthorizationAspect {
    private final JwtUtil jwtUtil;

    @Autowired
    public AdminAuthorizationAspect(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Before("@annotation(com.example.ex1curs9.annotations.RequireAdmin)")
    public void checkAdminRole(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new UnauthorizedException("No request context found");
        }

        HttpServletRequest request = attributes.getRequest();
        String authHeader = request.getHeader("Authorization");
        System.out.println("authHeader: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("No authorization token found");
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            throw new UnauthorizedException("Invalid token");
        }

        User.Role role = jwtUtil.getRoleFromToken(token);
        if (role != User.Role.ADMIN) {
            throw new UnauthorizedException("Admin access required");
        }
    }
}
