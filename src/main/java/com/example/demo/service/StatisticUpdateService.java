package com.example.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StatisticUpdateService {

    private final StatisticService statisticService;

    public StatisticUpdateService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    // Метод для обновления статистики из файла "test_report.json"
    private void updateStatisticsFromJsonFile() {
        // Логика обновления статистики из файла
        System.out.println("Updating statistics from test_report.json...");
    }

    // Аннотация @Scheduled для запуска метода updateStatisticsFromJsonFile
    // через определенные интервалы времени
    @Scheduled(fixedRate = 60000) // обновление каждую минуту (60000 миллисекунд)
    public void scheduleUpdate() {
        updateStatisticsFromJsonFile();
    }

    // Метод для инвалидации кэша после обновления статистики
    @CacheEvict(value = {"statisticsByDate", "statisticsByAsin"}, allEntries = true)
    public void evictCache() {
        // Метод для инвалидации кэша
    }
}
