package com.example.demo.repository;

import com.example.demo.model.SalesTraffic;
import org.springframework.data.mongodb.repository.MongoRepository;

// Определяет репозиторий для работы с коллекцией SalesTraffic в MongoDB
public interface SalesTrafficRepository extends MongoRepository<SalesTraffic, String> {
    // Наследует базовые CRUD операции от MongoRepository для объектов SalesTraffic
    // По умолчанию включает методы для сохранения, поиска, обновления и удаления документов
}
