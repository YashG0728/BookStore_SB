package com.example.bookstore.model;

import com.example.bookstore.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@Table(name = "book_store",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private  String password;

    private int is_login;

    public UserModel(UserDTO userDTO, int userId) {
        userId = userId;
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.dob = userDTO.getDob();
        this.password = userDTO.getPassword();
    }

    public UserModel(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.dob = userDTO.getDob();
        this.password = userDTO.getPassword();
    }
}
