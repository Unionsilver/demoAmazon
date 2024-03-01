package com.example.demo;

import com.example.demo.model.SalesTraffic; // Убедитесь, что импортируется правильный путь
import com.example.demo.model.SaleStatistics; // Предполагается, что у вас есть такой класс
import com.example.demo.repository.SalesTrafficRepository;
import com.example.demo.repository.SaleStatisticsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader {
    private final SalesTrafficRepository salesTrafficRepository;
    private final SaleStatisticsRepository saleStatisticsRepository;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    public DataLoader(SalesTrafficRepository salesTrafficRepository, SaleStatisticsRepository saleStatisticsRepository, ObjectMapper objectMapper) {
        this.salesTrafficRepository = salesTrafficRepository;
        this.saleStatisticsRepository = saleStatisticsRepository;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void loadData() {
        loadSalesTrafficData(); // Загрузка данных о трафике продаж
        loadSaleStatisticsData(); // Загрузка статистики продаж
    }

    private void loadSalesTrafficData() {
        Resource resource = new ClassPathResource("test_report.json"); // Загрузка ресурса - файла JSON из classpath
        try (InputStream inputStream = resource.getInputStream()) { // Открытие потока чтения файла
            List<SalesTraffic> dataList = objectMapper.readValue(inputStream, new TypeReference<List<SalesTraffic>>(){}); // Чтение JSON и преобразование его в список объектов SalesTraffic
            salesTrafficRepository.saveAll(dataList); // Сохранение списка объектов SalesTraffic в репозитории
            logger.info("Sales traffic data loaded successfully."); // Логирование успешной загрузки данных о трафике продаж
        } catch (IOException e) { // Обработка исключений ввода-вывода
            logger.error("Error loading sales traffic data", e); // Логирование ошибки при загрузке данных о трафике продаж
        }
    }

    private void loadSaleStatisticsData() {
        Resource resource = new ClassPathResource("test_report.json"); // Загрузка ресурса - файла JSON из classpath
        try (InputStream inputStream = resource.getInputStream()) { // Открытие потока чтения файла
            List<SaleStatistics> dataList = objectMapper.readValue(inputStream, new TypeReference<List<SaleStatistics>>(){}); // Чтение JSON и преобразование его в список объектов SaleStatistics
            saleStatisticsRepository.saveAll(dataList); // Сохранение списка объектов SaleStatistics в репозитории
            logger.info("Sale statistics data loaded successfully."); // Логирование успешной загрузки статистики продаж
        } catch (IOException e) { // Обработка исключений ввода-вывода
            logger.error("Error loading sale statistics data", e); // Логирование ошибки при загрузке статистики продаж
        }
    }

}
