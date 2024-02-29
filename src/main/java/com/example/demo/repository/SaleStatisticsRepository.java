package com.example.demo.repository;


import com.example.demo.SaleStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaleStatisticsRepository extends MongoRepository<SaleStatistics, String> {
    // Тут можна додати додаткові методи запитів, якщо потрібно
}
