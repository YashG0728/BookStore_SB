package com.example.bookstore.repository;

import com.example.bookstore.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartModel,Integer> {

}
