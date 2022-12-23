package com.example.bookstore.dto;

import lombok.Data;

@Data
public class BookDTO {
    int bookId;
    String bookName;
    String authorName;
    String bookDescription;
    String booking;
    long price;
    int quantity;
}
