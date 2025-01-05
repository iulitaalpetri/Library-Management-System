package com.example.ex1curs9.service;

import com.example.ex1curs9.exception.CartNotFoundException;
import com.example.ex1curs9.exception.UserNotFoundException;
import com.example.ex1curs9.model.Book;
import com.example.ex1curs9.model.Cart;
import com.example.ex1curs9.model.User;
import com.example.ex1curs9.repository.BookRepository;
import com.example.ex1curs9.repository.CartRepository;
import com.example.ex1curs9.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart createCart(Long userId) {
        if(cartRepository.existsByUserId(userId)) {
            throw new IllegalStateException("Cart already exists for this user");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        Cart cart = new Cart(user);
        return cartRepository.save(cart);
    }

    public void addBookToCart(Long cartId, Long bookId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException());

        if(cartRepository.existsBookInCart(cartId, bookId)) {
            throw new IllegalStateException("Book already in cart");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException());

        cart.addBook(book);
        cartRepository.save(cart);
    }

    public void removeBookFromCart(Long cartId, Long bookId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException());

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException());

        cart.removeBook(book);
        cartRepository.save(cart);
    }

    public long getCartItemCount(Long cartId) {
        return cartRepository.countBooksByCartId(cartId);
    }

    public void clearCart(Long cartId) {
        cartRepository.clearCart(cartId);
    }
}