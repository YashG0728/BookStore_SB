package com.example.bookstore.service;

import com.example.bookstore.dto.CartDto;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.model.BookModel;
import com.example.bookstore.model.CartModel;
import com.example.bookstore.model.UserModel;
import com.example.bookstore.repository.CartRepo;
import com.example.bookstore.repository.Irepo;
import com.example.bookstore.repository.Irepobook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartServiceInterface {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    private Irepo irepo;
    @Autowired
    private Irepobook irepobook;

    public CartModel insertIntoCart(CartDto cartDto) {
        Optional<UserModel> user = irepo.findById(cartDto.getUserId());
        Optional<BookModel> book = irepobook.findById(cartDto.getBookId());
        if (user.isPresent() && book.isPresent()) {
            CartModel addedItem = new CartModel(book.get(), user.get(), cartDto.getQuantity());
            cartRepo.save(addedItem);
            return addedItem;
        } else throw new BookStoreException("book id does not exists");
    }

    @Override
    public List<CartModel> getAllcartItems() {
        List<CartModel> cartModels = cartRepo.findAll();
        return cartModels;
    }

    @Override
    public Optional<CartModel> getCartById(int id) {
        Optional<CartModel> cart = cartRepo.findById(id);
        if (cart.isPresent()) {
            return cart;
        } else {
            throw new BookStoreException("this cartId is not Present");
        }
    }
    @Override
    public CartModel updateByCartId(int id, CartDto cartDto) {
        Optional<CartModel> cart = cartRepo.findById(id);
        Optional<UserModel> user = irepo.findById(cartDto.getUserId());
        Optional<BookModel> book = irepobook.findById(cartDto.getBookId());
        if(cart.isPresent() && user.isPresent() && book.isPresent()){
            CartModel cartModel = new CartModel(id,book.get(),user.get(),cartDto.getQuantity());
            cartRepo.save(cartModel);
            return cartModel;
        } else throw new BookStoreException("cart not present of this Id");
    }

    @Override
    public List<CartModel> deleteByCartId(int id) {
        Optional<CartModel> book = cartRepo.findById(id);
        if (book.isPresent()) {
            cartRepo.deleteById(id);
            return cartRepo.findAll();
        } else throw new BookStoreException("cart not present of this Id");
    }
    @Override
    public Optional<CartModel> updateQuantityById(int cartId, int quantity) {
        Optional<CartModel> cartModel = cartRepo.findById(cartId);
        if (cartModel.isPresent()) {
            cartModel.get().setQuantity(quantity);
            CartModel cart = new CartModel(cartModel.get().getCartId(), cartModel.get().getBookModel(),
                    cartModel.get().getUserModel(), cartModel.get().getQuantity());
            cartRepo.save(cart);
            return cartModel;
        } else throw new BookStoreException("Id not present to update quantity");
    }
}

