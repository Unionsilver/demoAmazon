package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// Класс для глобальной обработки исключений в приложении
@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработка исключения, когда пользователь уже существует в базе данных
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException e, WebRequest request) {
        // Создание объекта ErrorDetails с информацией об ошибке
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(), // Статус код ошибки
                e.getMessage(), // Сообщение об ошибке
                request.getDescription(false) // Детали запроса, который привел к ошибке
        );
        // Возвращение ответа с указанием статуса ошибки и информацией об ошибке
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Внутренний класс для передачи информации об ошибке клиенту
    private static class ErrorDetails {
        private int statusCode; // HTTP статус код ошибки
        private String message; // Сообщение об ошибке
        private String details; // Детали ошибки

        // Конструктор класса ErrorDetails
        public ErrorDetails(int statusCode, String message, String details) {
            this.statusCode = statusCode;
            this.message = message;
            this.details = details;
        }

        // Геттер для получения статус кода ошибки
        public int getStatusCode() {
            return statusCode;
        }

        // Геттер для получения сообщения об ошибке
        public String getMessage() {
            return message;
        }

        // Геттер для получения деталей ошибки
        public String getDetails() {
            return details;
        }
    }

    // Место для добавления других обработчиков исключений...
}
