package com.example.bookstore.repository;

import com.example.bookstore.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface Irepo extends JpaRepository<UserModel,Integer> {
    @Query(value = "select * from book_store where email= :email",nativeQuery = true)
    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> getByEmail(String email);
}
