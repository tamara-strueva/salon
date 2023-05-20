package com.salon.salon;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.salon.salon.exceptions.MasterNotFoundException;
import com.salon.salon.exceptions.CustomNotFoundExсeption;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Обработчик вызываемых в контроллерах ошибок
    @ExceptionHandler(MasterNotFoundException.class)
    public ResponseEntity<String> handleClientNotFoundException(MasterNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // @ExceptionHandler(ObjectNotFoundException.class)
    // public ResponseEntity<String> handleObjectNotFoundException(ObjectNotFoundException ex){
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    // }

    // @ExceptionHandler(OrderNotFoundException.class)
    // public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex){
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    // }

    // @ExceptionHandler(StaffNotFoundException.class)
    // public ResponseEntity<String> handleStaffNotFoundException(StaffNotFoundException ex){
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    // }

    // @ExceptionHandler(TypeNotFoundException.class)
    // public ResponseEntity<String> handleTypeNotFoundException(TypeNotFoundException ex){
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    // }

    @ExceptionHandler(CustomNotFoundExсeption.class)
    // Метод возвращает статус и сообщение об ошибке. Обернуто в map, чотобы не вызывать ошибку со статусом
    public ResponseEntity<Map<String, String>> handleCustomNotFoundException(CustomNotFoundExсeption ex){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("ERROR:", ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }
    
}
