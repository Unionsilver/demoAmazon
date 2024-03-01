package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Указывает, что данный класс является документом MongoDB и будет храниться в коллекции "users"
@Document(collection = "users")
public class User {

    @Id // Указывает, что поле является идентификатором документа
    private String id;
    private String username; // Имя пользователя
    private String password; // Пароль пользователя, будет храниться в зашифрованном виде
    private Set<String> roles = new HashSet<>(); // Набор ролей пользователя

    // Безаргументный конструктор необходим для работы с библиотеками Spring Data
    public User() {
    }

    // Конструктор с параметрами для удобства создания экземпляров класса
    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles; // Инициализация набора ролей пользователя
    }

    // Геттеры и сеттеры для доступа к полям класса
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
        this.roles = roles; // Установка набора ролей пользователя
    }

    // Переопределение метода equals для сравнения объектов User
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles); // Включение ролей в сравнение
    }

    // Переопределение метода hashCode для генерации хеш-кода объекта User
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, roles); // Включение ролей в хеш-код
    }

    // Переопределение метода toString для представления объекта User в виде строки
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" + // Защита пароля от вывода
                ", roles=" + roles + // Вывод списка ролей
                '}';
    }
}
