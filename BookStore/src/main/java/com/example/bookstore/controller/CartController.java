package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.CartDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.model.BookModel;
import com.example.bookstore.model.CartModel;
import com.example.bookstore.service.CartService;
import com.example.bookstore.service.ICartServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    ICartServiceInterface iCartServiceInterface;

    @PostMapping("/InsertDataIntoCart")
    public ResponseEntity<ResponseDto> InserData(@RequestBody CartDto cartDto){
        CartModel cartModel = cartService.insertIntoCart(cartDto);
        ResponseDto responseDto = new ResponseDto("New User Register SuccessFully", cartModel);
        ResponseEntity<ResponseDto> response = new ResponseEntity(responseDto, HttpStatus.OK);
        return response;
    }

    @GetMapping("/getCarts")
    public List<CartModel> findAllCart() {
        return iCartServiceInterface.getAllcartItems();
    }

    @GetMapping("/getbyId/{id}")
    public ResponseEntity<ResponseDto> foundCartById(@PathVariable int id) {
        Optional<CartModel> foundCart = iCartServiceInterface.getCartById(id);
        ResponseDto responseDTO = new ResponseDto("Get Id successfully", foundCart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponseDto> deleteByCartId(@PathVariable int id) {
        List<CartModel> updatedCart = iCartServiceInterface.deleteByCartId(id);
        ResponseDto responseDTO = new ResponseDto("Id deleted successfully", updatedCart);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    @PutMapping("/updateBookById")
    public ResponseEntity<ResponseDto> updateByBookId(@RequestParam int bookId, @RequestBody CartDto cartDto) {
        CartModel updatedCart = iCartServiceInterface.updateByCartId(bookId, cartDto);
        ResponseDto responseDTO = new ResponseDto("Updated book successfully", updatedCart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateCartQuantityById/{id}")
    public ResponseEntity<ResponseDto> updateQuantityById(@PathVariable int id, @RequestParam int quantity) {
        Optional<CartModel> updatedQuantity = iCartServiceInterface.updateQuantityById(id, quantity);
        ResponseDto responseDTO = new ResponseDto("update quantity successfully", updatedQuantity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
