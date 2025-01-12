package com.example.ex1curs9.controller;


import com.example.ex1curs9.annotations.RequireAdmin;
import com.example.ex1curs9.dto.UserDto;
import com.example.ex1curs9.exception.UserNotFoundException;
import com.example.ex1curs9.mapper.LoginRequestDto;
import com.example.ex1curs9.mapper.LoginResponseDto;
import com.example.ex1curs9.mapper.RegisterRequestDto;
import com.example.ex1curs9.mapper.UserMapper;
import com.example.ex1curs9.model.User;
import com.example.ex1curs9.service.UserService;
import com.example.ex1curs9.utils.JwtUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.jwtUtil = new JwtUtil();
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegisterRequestDto registerRequest) {
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(registerRequest.getPassword());
        newUser.setRole(registerRequest.getRole());

        User createdUser = userService.create(newUser);
        return ResponseEntity.ok(userMapper.toDto(createdUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        Optional<User> userOptional = userService.findByUsername(loginRequest.getUsername());



        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String encodedPassword = Base64.getEncoder()
                    .encodeToString(loginRequest.getPassword().getBytes());


            if (encodedPassword.equals(user.getPassword())) {
                System.out.println("Authorized");
                String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
                System.out.println("Token: " + token);


                LoginResponseDto response = new LoginResponseDto(
                        userMapper.toDto(user),
                        token
                );

                System.out.println("TOKen" + token);

                return ResponseEntity.ok(response);
            }
        }

        System.out.println("Unauthorized");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        long idLong = Long.parseLong(id);
        return userService.findById(idLong)
                .map(user -> ResponseEntity.ok(userMapper.toDto(user)))
                .orElseThrow(() -> new UserNotFoundException());

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {

        Long idLong = Long.parseLong(id);
        User updatedUser = userService.update(idLong, userMapper.toEntity(userDto));
        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    @RequireAdmin
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {

        userService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");

        return ResponseEntity.ok(response);

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();

        return ResponseEntity.ok().build();

    }
}


