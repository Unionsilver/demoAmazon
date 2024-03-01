package com.example.demo.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private String username;

    public UserAlreadyExistsException(String message, String username) {
        super(message);
        this.username = username;
    }

    // Добавление геттера для нового поля
    public String getUsername() {
        return username;
    }

    // При необходимости добавьте другие поля или методы, которые могут быть полезны для обработки этого исключения
}
