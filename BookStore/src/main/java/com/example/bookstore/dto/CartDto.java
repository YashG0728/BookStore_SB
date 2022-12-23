package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {

    private int userId;

    private int bookId;

    private int quantity;

    public CartDto(int userId, int bookId, int quantity) {
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
