package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @ManyToOne
    @JoinColumn(name = "book_model_book_id")
    private BookModel bookModel;
    @OneToOne
    @JoinColumn(name = "user_model_user_id")
    private UserModel userModel;

    private int quantity;

    public CartModel(int cartId, BookModel bookModel, UserModel userModel, int quantity) {
        this.cartId = cartId;
        this.bookModel = bookModel;
        this.userModel = userModel;
        this.quantity = quantity;
    }

    public CartModel(BookModel bookModel, UserModel userModel, int quantity) {
        this.bookModel = bookModel;
        this.userModel = userModel;
        this.quantity = quantity;
    }
}
