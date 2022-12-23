package com.example.bookstore.repository;

import com.example.bookstore.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface Irepobook extends JpaRepository<BookModel,Integer> {
    @Query(value = "select * from store_books where book_Name=:bookName", nativeQuery = true)
    Optional<BookModel> getByBookName(String bookName);


    @Modifying
    @Query(value = "update store_books set quantity=:quantity where book_Id=:bookId", nativeQuery = true)
    void updateQuantity(int bookId, int quantity);

    @Query(value = "select * from store_books order by price asc ", nativeQuery = true)
    List<BookModel> sortInAsc();


    @Query(value = "select * from store_books order by price desc", nativeQuery = true)
    List<BookModel> sortedInDesc();
}
