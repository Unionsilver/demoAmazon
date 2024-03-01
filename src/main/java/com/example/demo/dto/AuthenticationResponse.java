package com.example.demo.dto;

// Класс DTO для ответа аутентификации, содержит JWT токен
public class AuthenticationResponse {

    private String jwt; // JWT токен для аутентификации пользователя

    // Конструктор без аргументов для корректной десериализации
    public AuthenticationResponse() {
    }

    // Конструктор с аргументом для удобства инициализации экземпляра класса с JWT
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    // Геттер для получения JWT токена
    public String getJwt() {
        return jwt;
    }

    // Сеттер для установки JWT токена
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
