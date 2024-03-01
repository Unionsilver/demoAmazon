package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

public class AuthenticationRequest {
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    // Конструктор без аргументов нужен для корректной десериализации JSON
    public AuthenticationRequest() {
    }

    // Конструктор с аргументами для удобства создания экземпляров
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
