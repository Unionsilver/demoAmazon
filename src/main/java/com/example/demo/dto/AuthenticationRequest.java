package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

// Класс DTO для запроса аутентификации, содержит информацию о пользователе
public class AuthenticationRequest {
    @NotBlank(message = "Username cannot be empty") // Валидация: поле не может быть пустым
    private String username; // Имя пользователя

    @NotBlank(message = "Password cannot be empty") // Валидация: поле не может быть пустым
    private String password; // Пароль пользователя

    // Конструктор без аргументов нужен для корректной десериализации JSON
    public AuthenticationRequest() {
    }

    // Конструктор с аргументами для удобства создания экземпляров
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Геттер для получения имени пользователя
    public String getUsername() {
        return username;
    }

    // Сеттер для установки имени пользователя
    public void setUsername(String username) {
        this.username = username;
    }

    // Геттер для получения пароля пользователя
    public String getPassword() {
        return password;
    }

    // Сеттер для установки пароля пользователя
    public void setPassword(String password) {
        this.password = password;
    }
}
