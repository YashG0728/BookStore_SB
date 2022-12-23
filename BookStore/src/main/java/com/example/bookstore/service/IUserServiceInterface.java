package com.example.bookstore.service;

import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.dto.LoginDto;
import com.example.bookstore.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserServiceInterface {

     UserModel addUser(UserDTO userDTO);

     List<UserModel> getAllData();

     UserModel getByUserId(int id);

    Optional<UserModel> findByEmail(String email);

    UserModel getByToken(String token);

     UserModel updateByToken(int id, String token, UserDTO addressBookDto);


    Optional<UserModel> login(LoginDto loginDto);

    UserModel changePassword(String token, String newPassword);


    UserModel resetPassword(String email, LoginDto loginDto, String token1);

    void deleteById(int id);

//    BookStoreModel updateByEmail(String email, BookStoreDto bookStoreDto);
}
