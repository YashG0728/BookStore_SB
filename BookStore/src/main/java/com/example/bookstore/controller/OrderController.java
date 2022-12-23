package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.model.OrderModel;
import com.example.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService iOrderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDto> placeOrder(@RequestBody OrderDto orderDTO) {
        OrderModel newOrderEntity = iOrderService.orderItem(orderDTO);
        ResponseDto responseDTO = new ResponseDto("Details added Successfully", newOrderEntity);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/orderservices/{orderId}")
    public ResponseEntity<ResponseDto> getBookById(@PathVariable int orderId){
        OrderModel order = iOrderService.getOrder(orderId);
        return new ResponseEntity<>(new ResponseDto("Get call for Id successful",order),HttpStatus.OK);

    }
    @PutMapping("/orderservice/{orderId}")
    public ResponseEntity<ResponseDto>cancelOrder(@PathVariable("orderId")int orderId, @RequestParam String token){
        OrderModel order = iOrderService.cancelOrder(orderId,token);
        return new ResponseEntity<>(new ResponseDto("Order cancelled successfully",order),HttpStatus.OK);
    }
    @GetMapping("/GetAllDataOrder")
    public List<OrderModel> FindAllData() {
        return iOrderService.getAllData();
    }

    @PutMapping("/updateByOrder/{id}")
    public ResponseEntity<ResponseDto> updateByOrderId(@PathVariable int id, @RequestBody OrderDto orderDto) {
        OrderModel updatedBook = iOrderService.updateOrderRecord(id,orderDto);
        ResponseDto responseDTO = new ResponseDto("Updated book successfully", updatedBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
