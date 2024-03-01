package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// DTO класс для запроса регистрации пользователя
public class UserRegistrationRequest {
    @NotBlank(message = "Username cannot be empty") // Проверка на непустое значение username
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") // Ограничение длины username
    private String username;

    @NotBlank(message = "Password cannot be empty") // Проверка на непустое значение password
    @Size(min = 6, message = "Password must be at least 6 characters long") // Ограничение длины password
    private String password;

    // Конструктор без аргументов для JPA и библиотек десериализации
    public UserRegistrationRequest() {
    }

    // Конструктор с параметрами для удобства создания экземпляра класса
    public UserRegistrationRequest(String username, String password) {
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
