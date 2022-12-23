package com.example.bookstore.service;

import com.example.bookstore.Util.TokenUtil;
import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.model.BookModel;
import com.example.bookstore.model.OrderModel;
import com.example.bookstore.model.UserModel;
import com.example.bookstore.repository.Irepo;
import com.example.bookstore.repository.Irepobook;
import com.example.bookstore.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    Irepobook irepobook;

    @Autowired
    Irepo irepo;

    @Autowired
    TokenUtil tokenUtil;


    @Override
    public OrderModel orderItem(OrderDto orderDTO) {
        Optional<UserModel> user = irepo.findById(orderDTO.getUserId());
        Optional<BookModel> book = irepobook.findById(orderDTO.getBookId());

        float totalPrice = book.get().getPrice() * orderDTO.getQuantity();
        if (user.isPresent()) {
            OrderModel order = new OrderModel(user.get(), book.get(), totalPrice, orderDTO.getQuantity(), orderDTO.getAddress(), orderDTO.cancel);
            orderRepo.save(order);
//            String token = tokenUtility.createToken(order.getOrderId());
//            Email emailModel = new Email(user.get().getEmail(), "Order Placed successfully ", "Hii." + user.get().getFirstName());
//            iEmail.sendMail(emailModel);
            return order;
        } else {
            throw new BookStoreException("User id or book id did not match! Please check and try again!");
        }


    }

    @Override
    public OrderModel getOrder(int orderId) {

        Optional<UserModel> optionalUserData = irepo.findById(orderId);
        if (optionalUserData.isPresent()) {
            return orderRepo.findById(orderId).get();
        } else {
            throw new BookStoreException("id not found");
        }
    }

    @Override
    public OrderModel cancelOrder(int orderId, String token) {

        int userId = tokenUtil.decodeToken(token);
        Optional<UserModel> optionalUserData = irepo.findById(userId);
        if (optionalUserData.isPresent()) {
            OrderModel order = orderRepo.findById(orderId).get();
            order.setCancel(true);
            BookModel book = order.getBook();
            book.setQuantity(book.getQuantity() + order.getQuantity());
            irepobook.save(book);
            order.setBook(book);
            return orderRepo.save(order);
        } else {
            throw new BookStoreException(token);
        }
    }

    @Override
    public List<OrderModel> getAllData() {
        List<OrderModel> orderModels = orderRepo.findAll();
        return orderModels;
    }

    @Override
    public OrderModel updateOrderRecord(Integer id, OrderDto dto) {
            Optional<UserModel> user = irepo.findById(dto.userId);
            Optional<BookModel> book = irepobook.findById(dto.bookId);
            float totalPrice = book.get().getPrice()* dto.quantity;
            if (user.isPresent()) {
                OrderModel order = new OrderModel(user.get(),book.get(),totalPrice,dto.quantity,dto.address,dto.cancel);
                order.setOrderId(id);
                return orderRepo.save(order);
            } else {
                throw new BookStoreException("User id or book id did not match! Please check and try again!");
            }
        }
    }
    
