package com.example.bookstore.model;

import com.example.bookstore.dto.BookDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "store_books")
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookId;
    String bookName;
    String authorName;
    String bookDescription;
    String booking;
    long price;
    int quantity;

    public BookModel(BookDTO bookDto) {
        this.bookName = bookDto.getBookName();
        this.authorName = bookDto.getAuthorName();
        this.bookDescription = bookDto.getBookDescription();
        this.booking = bookDto.getBooking();
        this.price = bookDto.getPrice();
        this.quantity = bookDto.getQuantity();
    }

    public BookModel(int bookId, BookDTO bookDTO) {
        this.bookId = bookId;
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.bookDescription = bookDTO.getBookDescription();
        this.booking = bookDTO.getBooking();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
    }
}
