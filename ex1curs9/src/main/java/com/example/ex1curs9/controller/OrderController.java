package com.example.ex1curs9.controller;

import com.example.ex1curs9.annotations.RequireAdmin;
import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.dto.OrderDto;
import com.example.ex1curs9.exception.UserNotFoundException;
import com.example.ex1curs9.mapper.OrderMapper;
import com.example.ex1curs9.model.Cart;
import com.example.ex1curs9.model.Order;
import com.example.ex1curs9.model.User;
import com.example.ex1curs9.service.CartService;
import com.example.ex1curs9.service.OrderService;
import com.example.ex1curs9.service.UserService;
import com.example.ex1curs9.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final CartService cartService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService,
                           JwtUtil jwtUtil,
                           UserService userService,
                           CartService cartService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.cartService = cartService;
        this.orderMapper = orderMapper;
    }


    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestHeader("Authorization") String authHeader) {
        if (!authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authHeader.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());


        Cart cart = cartService.getCartByUserId(user.getId());
        if (cart.getBooks().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }


        List<Long> bookIds = cart.getBooks().stream()
                .map(Book::getId)
                .collect(Collectors.toList());

        Order order = orderService.createOrder(user.getId(), bookIds);


        cartService.clearCart(cart.getId());

        return ResponseEntity.ok(orderMapper.toDto(order));
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderDto>> getUserOrderHistory(
            @RequestHeader("Authorization") String authHeader) {
        if (!authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authHeader.substring(7);
        String username = jwtUtil.getUsernameFromToken(token);

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());

        List<Order> orders = orderService.getUserOrderHistory(user);
        return ResponseEntity.ok(orderMapper.toDtoList(orders));
    }


    @GetMapping
    @RequireAdmin
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.toDtoList(orders));
    }
}
