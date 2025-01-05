package com.example.ex1curs9.service;

import com.example.ex1curs9.exception.DuplicateUserException;
import com.example.ex1curs9.exception.UserNotFoundException;
import com.example.ex1curs9.model.User;
import com.example.ex1curs9.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.Base64;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create user
    public User create(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        existingUser.ifPresent(u -> {
            throw new DuplicateUserException();
        });


        String encodedPassword = Base64.getEncoder()
                .encodeToString(user.getPassword().getBytes());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    // find user by id
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public Optional<User> findUserWithOrders(Long userId) {
        return userRepository.findUserWithOrders(userId);
    }


    public Optional<User> findUserWithCart(Long userId) {
        return userRepository.findUserWithCart(userId);
    }


    public List<User> findByRole(User.Role role) {
        return userRepository.findByRole(role);
    }


    public List<User> findAllWithOrders() {
        return userRepository.findAllWithOrders();
    }


    public User update(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    // Verifică dacă noul username există deja la alt user
                    if (!existingUser.getUsername().equals(updatedUser.getUsername()) &&
                            userRepository.existsByUsername(updatedUser.getUsername())) {
                        throw new DuplicateUserException();
                    }

                    existingUser.setUsername(updatedUser.getUsername());
                    if (updatedUser.getPassword() != null) {
                        // Folosim direct Base64 pentru encodare
                        String encodedPassword = Base64.getEncoder()
                                .encodeToString(updatedUser.getPassword().getBytes());
                        existingUser.setPassword(encodedPassword);
                    }
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserNotFoundException());
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }



}
