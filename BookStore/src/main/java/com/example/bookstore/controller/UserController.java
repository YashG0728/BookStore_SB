package com.example.bookstore.controller;

import com.example.bookstore.Util.TokenUtil;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.dto.LoginDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.model.UserModel;
import com.example.bookstore.model.Email;
import com.example.bookstore.service.UserService;
import com.example.bookstore.service.IUserServiceInterface;
import com.example.bookstore.service.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    IUserServiceInterface iUserServiceInterface;
    @Autowired
    IEmailService iEmailService;
    @Autowired
    TokenUtil tokenUtil;

    @PostMapping("/Post")
    public ResponseEntity<ResponseDto> Register(@RequestBody UserDTO userDTO) {
        UserModel userModel = iUserServiceInterface.addUser(userDTO);
        String tokenId = tokenUtil.createToken(userModel.getUserId());
        Email email = new Email(userModel.getEmail(), "Message", tokenId);
        iEmailService.sendMail(email);
        ResponseDto responseDto = new ResponseDto("New User Register SuccessFully", userModel, tokenId);
        ResponseEntity<ResponseDto> response = new ResponseEntity(responseDto, HttpStatus.OK);
        log.info(String.valueOf(tokenId));
        return response;
    }

    @GetMapping("/GetAllData")
    public List<UserModel> FindAllData() {
        return iUserServiceInterface.getAllData();
    }

    @GetMapping("/get/{id}")
    public UserModel getDataUsingId(@PathVariable int id) {

        return iUserServiceInterface.getByUserId(id);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<ResponseDto> findByEmail(@PathVariable("email") String email) {
        Optional<UserModel> list = null;
        list = service.findByEmail(email);
        ResponseDto responseDto = new ResponseDto("Employee by email!!!", list, null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    @PutMapping("/updateUsingToken/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable int id, @RequestParam String token, @RequestBody UserDTO userDTO) throws BookStoreException {
        UserModel userModel = iUserServiceInterface.updateByToken(id, token, userDTO);
        ResponseDto responseDto = new ResponseDto("updated user", userModel, token);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    @GetMapping("/getByToken/{token}")
    public ResponseEntity<ResponseDto> getByToken(@PathVariable String token) {
        UserModel userModel = iUserServiceInterface.getByToken(token);
        ResponseDto responseDto = new ResponseDto("get by token", userModel, token);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

//    @PostMapping("userservice/login")
//    public ResponseEntity<ResponseDto> loginUser(@RequestBody LoginDto loginDto) {
//        Optional<UserModel> userModel = iUserServiceInterface.login(loginDto);
//        return new ResponseEntity<>(new ResponseDto("login successfully", userModel, null), HttpStatus.OK);
//    }

    @PostMapping("/changePassword/{token}")
    public UserModel changePassword(@PathVariable String token, @RequestParam String newPassword) {
        return service.changePassword(token,newPassword);
    }

    @PostMapping("/resetpassword/{email}")
    public ResponseEntity<ResponseDto> resetPassword(@PathVariable String email,@RequestBody LoginDto loginDto,@RequestParam String token2){
        UserModel userModel=iUserServiceInterface.resetPassword(email,loginDto,token2);
        ResponseDto responseDto=new ResponseDto("Password reset successfully",userModel,null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUsingId(@PathVariable int id) {

        iUserServiceInterface.deleteById(id);
    }

//    @PutMapping("/updateByEmailID/{email}")
//    public ResponseEntity<ResponseDto> updateByEmail(@PathVariable String email, @RequestBody BookStoreDto bookStoreDto) throws BookStoreException {
//        BookStoreModel bookStoreModel = iBookStoreInterface.updateByEmail(email, bookStoreDto);
//        ResponseDto responseDto = new ResponseDto("updated user", bookStoreModel, email);
//        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
//        return response;
//    }
}
