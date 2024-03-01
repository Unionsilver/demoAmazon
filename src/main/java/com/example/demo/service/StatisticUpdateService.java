package com.example.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

// Сервис для обновления статистики продаж
@Service
public class StatisticUpdateService {

    private final StatisticService statisticService; // Ссылка на сервис статистики для возможного взаимодействия

    // Внедрение зависимости StatisticService через конструктор
    public StatisticUpdateService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    // Приватный метод для обновления статистики из файла "test_report.json"
    private void updateStatisticsFromJsonFile() {
        // Здесь реализуется логика обновления статистики из файла
        System.out.println("Updating statistics from test_report.json...");
    }

    // Метод для периодического выполнения обновления статистики. Запускается по расписанию.
    @Scheduled(fixedRate = 60000) // Запуск метода каждые 60000 мс (1 минута)
    public void scheduleUpdate() {
        updateStatisticsFromJsonFile(); // Вызов метода обновления статистики
    }

    // Метод для очистки кэша после обновления статистики
    @CacheEvict(value = {"statisticsByDate", "statisticsByAsin"}, allEntries = true) // Инвалидация всех записей в кэше
    public void evictCache() {
        // Логика инвалидации кэша
        // Данный метод может быть вызван после обновления статистики для гарантии актуальности данных
    }
}
