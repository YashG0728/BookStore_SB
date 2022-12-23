package com.example.bookstore.service;

import com.example.bookstore.dto.CartDto;
import com.example.bookstore.model.CartModel;

import java.util.List;
import java.util.Optional;

public interface ICartServiceInterface {
    CartModel insertIntoCart(CartDto cartDto);

    List<CartModel> getAllcartItems();

    Optional<CartModel> getCartById(int id);

    CartModel updateByCartId(int id, CartDto cartDto);

    List<CartModel> deleteByCartId(int id);

    Optional<CartModel> updateQuantityById(int cartId, int quantity);
}
