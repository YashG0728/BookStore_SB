package com.example.bookstore.model;

import com.example.bookstore.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data

public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate localDate = LocalDate.now();
    private float price;
    private int quantity;
    private String address;
    @OneToOne
    @JoinColumn(name = "order_user_id")
    private UserModel user;
    @ManyToOne
    @JoinColumn(name = "order_book_id")
    private BookModel book;
    private boolean cancel;

    public OrderModel(UserModel user, BookModel book, float price, int quantity, String address, boolean cancel) {
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.user = user;
        this.book = book;
        this.cancel = cancel;
    }

    public OrderModel() {

    }

    public OrderModel(Integer id, int quantity, String address, BookModel bookModel, UserModel userModel, boolean cancel, String bookName) {
    }
}