package com.example.demo.dto;

public class AuthenticationResponse {

    private String jwt;

    // Конструктор без аргументов для корректной десериализации
    public AuthenticationResponse() {
    }

    // Конструктор с аргументом для удобства инициализации
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    // Геттеры и сеттеры
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
