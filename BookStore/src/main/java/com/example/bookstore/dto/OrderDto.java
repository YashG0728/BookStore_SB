package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class OrderDto {
    // @NotNull(message = "Quantity can not be null!")
    public int quantity;
    // @NotNull(message = "Address can not be null!")
    public String address;
    // @NotNull(message = "UserId can not be null")
    public int userId;
    // @NotNull(message = "BookId can not be null")
    public int bookId;

    public boolean cancel = false;
}
