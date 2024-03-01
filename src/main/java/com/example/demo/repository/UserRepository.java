package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

// Интерфейс репозитория для работы с объектами User в MongoDB
public interface UserRepository extends MongoRepository<User, String> {
    // Определяет метод для поиска пользователя по его имени пользователя
    // Этот метод автоматически реализуется Spring Data на основе его имени
    User findByUsername(String username);
}
