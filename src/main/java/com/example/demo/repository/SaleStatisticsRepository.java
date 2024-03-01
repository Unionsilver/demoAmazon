package com.example.demo.repository;

import com.example.demo.model.SaleStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleStatisticsRepository extends MongoRepository<SaleStatistics, String> {
    List<SaleStatistics> findBySaleDate(LocalDate saleDate);
}
