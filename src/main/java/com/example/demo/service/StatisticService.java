package com.example.demo.service;

import com.example.demo.model.SaleStatistics;
import com.example.demo.repository.SaleStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

// Сервис для работы со статистикой продаж
@Service
public class StatisticService {

    private final SaleStatisticsRepository saleStatisticsRepository; // Репозиторий для доступа к данным о статистике продаж

    // Внедрение зависимости через конструктор
    @Autowired
    public StatisticService(SaleStatisticsRepository saleStatisticsRepository) {
        this.saleStatisticsRepository = saleStatisticsRepository;
    }

    // Получение статистики по дате с кэшированием результатов
    @Cacheable(value = "statisticsByDate")
    public String getStatisticsByDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Форматтер для даты
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, formatter); // Парсинг строки даты в LocalDate
        } catch (DateTimeParseException e) {
            return "Error: Date format should be yyyy-MM-dd"; // Ошибка формата даты
        }

        List<SaleStatistics> statistics = saleStatisticsRepository.findBySaleDate(date); // Поиск статистики по дате
        if (statistics.isEmpty()) {
            return "No statistics found for date " + dateString; // Случай, когда статистика не найдена
        }

        // Формирование и возврат строки с результатом
        return "Statistics for date " + dateString + ": " + statistics.size() + " entries found.";
    }

    // Получение статистики по ASIN с кэшированием результатов
    @Cacheable(value = "statisticsByAsin")
    public String getStatisticsByAsin(String asin) {
        // Логика получения статистики по ASIN (примерная реализация)
        return "Statistics for ASIN " + asin;
    }
}
