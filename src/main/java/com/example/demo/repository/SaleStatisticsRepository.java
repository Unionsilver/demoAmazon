package com.example.demo.repository;

import com.example.demo.model.SaleStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// Указывает, что данный интерфейс является репозиторием Spring Data MongoDB
@Repository
public interface SaleStatisticsRepository extends MongoRepository<SaleStatistics, String> {
    // Метод для получения списка статистики продаж по определенной дате
    // Возвращает список объектов SaleStatistics, соответствующих указанной дате продажи
    List<SaleStatistics> findBySaleDate(LocalDate saleDate);
}
