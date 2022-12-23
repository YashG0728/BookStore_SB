package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.model.BookModel;
import com.example.bookstore.repository.Irepobook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookInterface {
    List<BookModel> book = new ArrayList();
    @Autowired
    Irepobook irepobook;

    @Override
    public BookModel insertBook(BookDTO bookDto) {
        BookModel bookModel = new BookModel(bookDto);
        return irepobook.save(bookModel);
    }

    @Override
    public List<BookModel> getAllBooks() {
        List<BookModel> bookModels = irepobook.findAll();
        return bookModels;
    }

    @Override
    public Optional<BookModel> getBookBYId(int bookId) {
        Optional<BookModel> book = irepobook.findById(bookId);
        if (book.isPresent()) {
            return book;
        } else {
            throw new BookStoreException("this bookId is not Present");
        }
    }

    @Override
    public Optional<BookModel> searchByBookName(String bookName) {
        Optional<BookModel> book = irepobook.getByBookName(bookName);
        if (book.isPresent()) {
            return book;
        } else {
            throw new BookStoreException("Book of this name is not present");
        }
    }

    @Override
    public BookModel updateByBookId(int bookId, BookDTO bookDTO) {
        Optional<BookModel> book = irepobook.findById(bookId);
        if (book.isPresent()) {
            BookModel updatedBook = new BookModel(bookId, bookDTO);
            irepobook.save(updatedBook);
            return updatedBook;
        } else throw new BookStoreException("Book not present of this Id");
    }

    @Override
    public List<BookModel> sortInAsc() {
        List<BookModel> sortedList = irepobook.sortInAsc();
        if (sortedList.isEmpty()) {
            throw new BookStoreException("Book not present for sorting");
        } else return sortedList;
    }

    @Override
    public Optional<BookModel> updateQuantity(int bookId, int quantity) {
        if (irepobook.existsById(bookId)) {
            irepobook.updateQuantity(bookId, quantity);
            return getBookBYId(bookId);
        } else {
            throw new BookStoreException("Book not present of this Id");
        }
    }

    @Override
    public List<BookModel> deleteByBookId(int id) {
        Optional<BookModel> book = irepobook.findById(id);
        if (book.isPresent()) {
            irepobook.deleteById(id);
            return irepobook.findAll();
        } else throw new BookStoreException("Book not present of this Id");
    }
    @Override
    public List<BookModel> sortedInDesc() {
        List<BookModel> sortedList = irepobook.sortedInDesc();
        if (sortedList.isEmpty()) {
            throw new BookStoreException("Book not present for sorting");
        } else return sortedList;
    }

    @Override
    public Optional<BookModel> getByBookName(String bookName) {
        Optional<BookModel> book = irepobook.getByBookName(bookName);
        if (book.isPresent()) {
            return book;
        } else {
            throw new BookStoreException("Book of this name is not present");
        }
    }
}
