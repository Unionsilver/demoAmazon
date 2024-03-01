package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private Set<String> roles = new HashSet<>(); // Добавление списка ролей

    // Безаргументный конструктор для использования Spring Data
    public User() {
    }

    // Конструктор с параметрами для удобства создания экземпляров
    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    // Переопределенные методы equals и hashCode
    // Включите поле roles в методы equals и hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, roles);
    }

    // Переопределенный метод toString
    // Обновите метод toString, чтобы включить информацию о ролях

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" + // Защита пароля
                ", roles=" + roles +
                '}';
    }
}
