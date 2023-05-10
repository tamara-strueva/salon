package com.salon.salon;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Обработчик вызываемых в контроллерах ошибок
    // @ExceptionHandler(ClientNotFoundException.class)
    // public ResponseEntity<String> handleClientNotFoundException(ClientNotFoundException ex){
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    // }

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

    // @ExceptionHandler(CustomNotFoundException.class)
    // // Метод возвращает статус и сообщение об ошибке. Обернуто в map, чотобы не вызывать ошибку со статусом
    // public ResponseEntity<Map<String, String>> handleCustomNotFoundException(CustomNotFoundException ex){
    //     Map<String, String> errorResponse = new HashMap<>();
    //     errorResponse.put("ERROR:", ex.getMessage());
    //     return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    // }
    
}
