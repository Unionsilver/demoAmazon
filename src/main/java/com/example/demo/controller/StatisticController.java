package com.example.demo.controller;

import com.example.demo.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Делает класс обработчиком HTTP запросов.
public class StatisticController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticController.class); // Инициализация логгера.
    private final StatisticService statisticService; // Ссылка на сервис статистики.

    @Autowired // Spring автоматически внедряет экземпляр StatisticService при создании экземпляра StatisticController.
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    // Метод для получения статистики по дате. Ожидает дату в качестве параметра запроса.
    @GetMapping("/statistics/by-date") // Определяет URL, по которому будет доступен метод.
    public ResponseEntity<String> getStatisticsByDate(@RequestParam("date") String date) {
        logger.info("Received request for statistics by date: {}", date); // Логирует получение запроса.
        String response = statisticService.getStatisticsByDate(date); // Получает статистику от сервиса.
        return ResponseEntity.ok(response); // Возвращает ответ с полученной статистикой и статусом 200 OK.
    }

    // Метод для получения статистики по ASIN. Ожидает ASIN в качестве параметра запроса.
    @GetMapping("/statistics/by-asin") // Определяет URL, по которому будет доступен метод.
    public ResponseEntity<String> getStatisticsByAsin(@RequestParam("asin") String asin) {
        logger.info("Received request for statistics by ASIN: {}", asin); // Логирует получение запроса.
        String response = statisticService.getStatisticsByAsin(asin); // Получает статистику от сервиса.
        return ResponseEntity.ok(response); // Возвращает ответ с полученной статистикой и статусом 200 OK.
    }
}
