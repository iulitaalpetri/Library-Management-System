package com.example.ex1curs9.service;

import com.example.ex1curs9.exception.IllegalOrderStateException;
import com.example.ex1curs9.exception.OrderAlreadyExistsException;
import com.example.ex1curs9.exception.OrderNotFoundException;
import com.example.ex1curs9.exception.UserNotFoundException;
import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Order;
import com.example.ex1curs9.model.User;
import com.example.ex1curs9.repository.BookRepository;
import com.example.ex1curs9.repository.OrderRepository;
import com.example.ex1curs9.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Order createOrder(Long userId, List<Long> bookIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());


        Order order = new Order(user);

        bookIds.forEach(bookId -> {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new BookNotFoundException());
            order.addBook(book);
        });

        return orderRepository.save(order);
    }

    public List<Order> getUserOrderHistory(User user) {
        return orderRepository.findByUserOrderByOrderDateDesc(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByOrderDateDesc();
    }


    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException());
    }


}
