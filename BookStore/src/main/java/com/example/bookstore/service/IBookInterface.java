package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.BookModel;
import java.util.List;
import java.util.Optional;

public interface IBookInterface {
    BookModel insertBook(BookDTO bookDto);

    List<BookModel> getAllBooks();

    Optional<BookModel> getBookBYId(int bookId);

    Optional<BookModel> searchByBookName(String bookName);

    BookModel updateByBookId(int bookId, BookDTO bookDTO);


    List<BookModel> sortInAsc();

    Optional<BookModel> updateQuantity(int bookId, int quantity);

    List<BookModel> deleteByBookId(int id);

    List<BookModel> sortedInDesc();

    Optional<BookModel> getByBookName(String bookName);
}
