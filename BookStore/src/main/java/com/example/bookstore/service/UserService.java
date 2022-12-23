package com.example.bookstore.service;

import com.example.bookstore.Util.TokenUtil;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.dto.LoginDto;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.model.UserModel;
import com.example.bookstore.model.Email;
import com.example.bookstore.repository.Irepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserServiceInterface {
    List<UserModel> bookModels = new ArrayList();
    @Autowired
    Irepo irepo;
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    IEmailService iEmailService;

    @Override
    public UserModel addUser(UserDTO userDTO) {
        UserModel userModel = new UserModel(userDTO);
        bookModels.add(userModel);
        irepo.save(userModel);
        return userModel;

    }

    @Override
    public List<UserModel> getAllData() {
        List<UserModel> userModels = irepo.findAll();
        return userModels;
    }

    @Override
    public UserModel getByUserId(int id) {
        UserModel userModel = irepo.findById(id).get();
        return userModel;
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {

        return irepo.findByEmail(email);
    }

    @Override
    public UserModel getByToken(String token) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserModel> addressBookModel = irepo.findById(userId);
        if (addressBookModel.isPresent()) {
            return irepo.findById(Integer.valueOf(userId)).get();
        } else {
            throw new BookStoreException("Token does not match");
        }
    }


    @Override
    public UserModel updateByToken(int id, String token, UserDTO addressBookDto) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserModel> addressBookModel = irepo.findById(userId);
        if (addressBookModel.isPresent()) {
            UserModel addressBookModel1 = new UserModel(addressBookDto);
            addressBookModel1.setUserId(userId);
            UserModel userModel = irepo.save(addressBookModel1);
            return userModel;

        } else {
            throw new BookStoreException("token never matched");
        }
    }

    @Override
    public Optional<UserModel> login(LoginDto loginDto) {
        Optional <UserModel> userModel1 = irepo.findByEmail(loginDto.getEmail());
        if (userModel1.isPresent()) {
            String passwordInDatabase = userModel1.get().getPassword();
            String passwordEntered = loginDto.getPassword();
            if (passwordEntered.equals(passwordInDatabase)) {
            } else {
                throw new BookStoreException("id not found");
            }
        } else {
            throw new BookStoreException("user not found");
        }
        return userModel1;
    }

    @Override
    public UserModel changePassword(String token, String newPassword) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = irepo.findById(id);
        isUserPresent.get().setPassword(newPassword);
        irepo.save(isUserPresent.get());
        return isUserPresent.get();
    }

    @Override
    public UserModel resetPassword(String email, LoginDto loginDto, String token1) {
        int tokenId = tokenUtil.decodeToken(token1);
        Optional<UserModel> optionalUserModel = irepo.findById(tokenId);
        Optional<UserModel> userModelOptional = irepo.getByEmail(email);
        if (userModelOptional.isPresent() && optionalUserModel.isPresent()) {
            UserModel userModel = irepo.getByEmail(email).get();
            userModel.setPassword(loginDto.getPassword());
            Email email1 = new Email(email, "password reset successfully", "NewPassword is :" + loginDto.getPassword());
            iEmailService.sendMail(email1);
            irepo.save(userModel);
            return userModel;
        } else {
            throw new BookStoreException("id not found");
        }
    }
    @Override
    public void deleteById(int id) {
        irepo.deleteById(id);
    }


//    @Override
//    public BookStoreModel updateByEmail(String email, BookStoreDto bookStoreDto) {
//        Optional<BookStoreModel> bookStoreModel = irepo.findByEmail(email);
//        if (bookStoreModel.isPresent()) {
//            BookStoreModel bookStoreModels = new BookStoreModel(bookStoreDto);
//            bookStoreModels.setEmail(email);
//            BookStoreModel bookstore = irepo.save(bookStoreModels);
//            return bookstore;
//        } else {
//            throw new BookStoreException("token never matched");
//        }
//    }
}
