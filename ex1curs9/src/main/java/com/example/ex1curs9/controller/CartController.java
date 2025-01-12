package com.example.ex1curs9.controller;

import com.example.ex1curs9.dto.CartDto;
import com.example.ex1curs9.exception.UserNotFoundException;
import com.example.ex1curs9.mapper.CartMapper;
import com.example.ex1curs9.model.Cart;
import com.example.ex1curs9.model.User;
import com.example.ex1curs9.service.CartService;
import com.example.ex1curs9.service.UserService;
import com.example.ex1curs9.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final CartMapper cartMapper;

    @Autowired
    public CartController(CartService cartService, JwtUtil jwtUtil, UserService userService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.cartMapper = cartMapper;
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<CartDto> addBookToCart(
            @PathVariable Long bookId,
            @RequestHeader("Authorization") String authHeader) {


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authHeader.substring(7);


        String username = jwtUtil.getUsernameFromToken(token);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }



        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());


        Cart cart = cartService.getCartByUserId(user.getId());
        cartService.addBookToCart(cart.getId(), bookId);


        return ResponseEntity.ok(cartMapper.toDto(cart));
    }



    @GetMapping("/total")
    public ResponseEntity<Double> getTotalPrice(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = authHeader.substring(7);

        String username = jwtUtil.getUsernameFromToken(token);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());

        Cart cart = cartService.getCartByUserId(user.getId());
        double totalPrice = cartService.calculateTotalPrice(cart.getId());

        return ResponseEntity.ok(totalPrice);
    }


}