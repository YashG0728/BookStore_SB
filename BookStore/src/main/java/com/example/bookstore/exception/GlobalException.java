package com.example.bookstore.exception;


import com.example.bookstore.dto.ResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {


        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errMsg = errorList.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        ResponseDto responseDTO = new ResponseDto("Exception While Processing  REST  Request",errMsg,null);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<ResponseDto> handleEmployeePayrollException(BookStoreException exception)
    {

        ResponseDto responseDTO = new ResponseDto("Exception While Processing REST Request",exception.getMessage(),null);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}

