package com.example.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Date;

@Data
public class UserDTO {
    private int UserId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String password;

}
