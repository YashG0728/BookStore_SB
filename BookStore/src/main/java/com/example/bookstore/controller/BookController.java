package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.model.BookModel;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.IBookInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    IBookInterface iBookInterface;



    @PostMapping("/PostBook")
    public ResponseEntity<ResponseDto> insertBookData(@RequestBody BookDTO bookDto) {
        BookModel bookModel = bookService.insertBook(bookDto);
        ResponseDto responseDto = new ResponseDto("New User Register SuccessFully", bookModel);
       ResponseEntity<ResponseDto> response = new ResponseEntity(responseDto, HttpStatus.OK);
        return response;
    }
    @GetMapping("/GetAllBooks")
    public List<BookModel> FindAllBooks() {
        return iBookInterface.getAllBooks();
    }

    @GetMapping("/getbyId/{bookId}")
    public ResponseEntity<ResponseDto> getByBookId(@PathVariable int bookId) {
        Optional<BookModel> foundBook = iBookInterface.getBookBYId(bookId);
        ResponseDto responseDTO = new ResponseDto("Get Id successfully", foundBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateBookById")
    public ResponseEntity<ResponseDto> updateByBookId(@RequestParam int bookId, @RequestBody BookDTO bookDTO) {
        BookModel updatedBook = iBookInterface.updateByBookId(bookId, bookDTO);
        ResponseDto responseDTO = new ResponseDto("Updated book successfully", updatedBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/sortInAsc")
    public ResponseEntity<ResponseDto> sortInAsc() {
        List<BookModel> sortedList = iBookInterface.sortInAsc();
        ResponseDto responseDTO = new ResponseDto("Sorted in ascending order successfully", sortedList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    @PutMapping("/updateQuantity/{bookId}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable int bookId, @RequestParam int quantity) {
        Optional<BookModel> updatedQuantity = iBookInterface.updateQuantity(bookId, quantity);
        ResponseDto responseDTO = new ResponseDto("update quantity successfully", updatedQuantity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{bookId}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int bookId) {
        List<BookModel> updatedData = iBookInterface.deleteByBookId(bookId);
        ResponseDto responseDTO = new ResponseDto("Id deleted successfully", updatedData);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    @GetMapping("/sortInDesc")
    public ResponseEntity<ResponseDto> sortInDesc() {
        List<BookModel> sortedList = iBookInterface.sortedInDesc();
        ResponseDto responseDTO = new ResponseDto("Sorted in descending order successfully", sortedList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getbyBookName")
    public ResponseEntity<ResponseDto> getByBookName(@RequestParam String bookName) {
        Optional<BookModel> foundBook = iBookInterface.getByBookName(bookName);
        ResponseDto responseDTO = new ResponseDto("Get book successfully", foundBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);

    }
}
