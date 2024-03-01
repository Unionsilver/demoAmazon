package com.example.demo.model;

// Класс, представляющий запрос на вход пользователя
public class LoginRequest {
    private String username; // Имя пользователя
    private String password; // Пароль пользователя

    // Конструктор без аргументов, необходимый для фреймворков и библиотек, которые используют рефлексию для создания экземпляров классов
    public LoginRequest() {
    }

    // Конструктор с аргументами для удобства создания экземпляра класса с предварительно заданными значениями
    public LoginRequest(String username, String password) {
        this.username = username; // Установка имени пользователя
        this.password = password; // Установка пароля пользователя
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
