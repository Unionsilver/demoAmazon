package com.example.demo.service;

import com.example.demo.model.SaleStatistics;
import com.example.demo.repository.SaleStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class StatisticService {

    private final SaleStatisticsRepository saleStatisticsRepository;

    @Autowired
    public StatisticService(SaleStatisticsRepository saleStatisticsRepository) {
        this.saleStatisticsRepository = saleStatisticsRepository;
    }

    @Cacheable(value = "statisticsByDate")
    public String getStatisticsByDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            return "Error: Date format should be yyyy-MM-dd";
        }

        List<SaleStatistics> statistics = saleStatisticsRepository.findBySaleDate(date);
        if (statistics.isEmpty()) {
            return "No statistics found for date " + dateString;
        }

        // Пример простой обработки и формирования строки с результатом.
        // В вашем случае логика может быть другой.
        return "Statistics for date " + dateString + ": " + statistics.size() + " entries found.";
    }

    @Cacheable(value = "statisticsByAsin")
    public String getStatisticsByAsin(String asin) {
        // Логика получения статистики по ASIN
        return "Statistics for ASIN " + asin;
    }
}
